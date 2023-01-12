package antessio.dddmodulith.ecommerce.payments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import antessio.dddmodulith.ecommerce.common.DomainService;
import antessio.dddmodulith.ecommerce.common.MessageBroker;
import antessio.dddmodulith.ecommerce.common.SerializationService;
import antessio.dddmodulith.ecommerce.payment.PaymentEvent;

public class PaymentService extends DomainService<Payment> {

    private PaymentRepository paymentRepository;

    private final Map<String, Account> accounts = new HashMap<>();

    private final Map<String, List<FundLock>> fundLocked = new HashMap<>();

    public PaymentService(
            SerializationService serializationService,
            MessageBroker messageBroker,
            PaymentRepository paymentRepository) {
        super(serializationService, messageBroker);
        this.paymentRepository = paymentRepository;
        Random random = new Random();
        IntStream.range(1, 100)
                 .mapToObj(i -> new Account(String.format("%05d", i), random.nextLong(0,9999)))
                .peek(a -> System.out.printf("account id:  %s and availability %d%n", a.id(), a.availability()))
                 .forEach(a -> accounts.put(a.id(), a));

    }


    Payment createFundLock(String accountId, Long amountUnit) {
        Account account = Optional.ofNullable(accounts.get(accountId))
                                  .orElseThrow(() -> new IllegalArgumentException("account not found"));
        if (account.availability() < amountUnit) {
            throw new IllegalArgumentException("not enough availability");
        }
        accounts.put(accountId, new Account(account.id(), account.availability() - amountUnit));
        FundLock fundLock = new FundLock(accountId, amountUnit);
        Optional.ofNullable(fundLocked.get(accountId))
                .ifPresentOrElse(
                        l -> l.add(fundLock),
                        () -> fundLocked.put(accountId, new ArrayList<>(List.of(fundLock))));
        Payment payment = Payment.of("fund-locked", accountId, amountUnit, null);
        return saveAndNotify(payment, getEventName(payment));
    }





    @Override
    protected Payment save(Payment obj) {
        paymentRepository.save(obj);
        return obj;
    }

    @Override
    protected Object convertToEvent(Payment obj) {
        return new PaymentEvent(obj.getId(), obj.getStatus(), obj.getAccountId(), obj.getAmountUnit(), obj.getOrderId());
    }

    Payment updateOrderId(Payment payment,
                       String orderId){
        return payment.withOrderId(orderId);
    }


    Payment reversePayment(Payment payment) {
        removeFundLok(payment);
        Account account = accounts.get(payment.getAccountId());
        accounts.put(account.id(), new Account(account.id(), account.availability() + payment.getAmountUnit()));
        return payment.withStatus("failed");
    }

    private void removeFundLok(Payment payment) {
        List<FundLock> fundLocks = fundLocked.get(payment.getAccountId());
        fundLocked.put(payment.getAccountId(), fundLocks.stream()
                                                        .filter(fl -> !fl.amountUnit().equals(payment.getAmountUnit()))
                                                        .collect(Collectors.toList()));
    }

    private String getEventName(Payment payment) {
        return String.format("payment-%s", payment.getStatus());
    }

    Payment executePayment(Payment payment) {
        removeFundLok(payment);
        return payment.withStatus("executed");
    }

    Optional<Payment> loadById(String paymentId) {
        return paymentRepository.getPaymentById(paymentId);
    }

}
