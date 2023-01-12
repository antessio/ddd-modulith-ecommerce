package antessio.dddmodulith.ecommerce.payments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import antessio.dddmodulith.ecommerce.common.CommonService;
import antessio.dddmodulith.ecommerce.common.MessageBroker;
import antessio.dddmodulith.ecommerce.common.SerializationService;
import antessio.dddmodulith.ecommerce.common.Subscriber;
import antessio.dddmodulith.ecommerce.order.OrderEvent;
import antessio.dddmodulith.ecommerce.payment.PaymentEvent;

public class PaymentService extends CommonService<Payment> {

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
                 .mapToObj(i -> new Account(String.format("%05d", i), random.nextLong()))
                 .forEach(a -> accounts.put(a.id(), a));
        messageBroker.subscribe(Subscriber.of("order-completed", orderCreated -> {
            OrderEvent orderCreatedEvent = serializationService.deserialize(orderCreated, OrderEvent.class);
            this.onOrderCompleted(orderCreatedEvent);
        }));
        messageBroker.subscribe(Subscriber.of("order-failed", orderFailed -> {
            OrderEvent orderFailedEvent = serializationService.deserialize(orderFailed, OrderEvent.class);
            this.onOrderFailed(orderFailedEvent);

        }));

    }

    private void onOrderFailed(OrderEvent orderFailedEvent) {
        Payment payment = paymentRepository.getPaymentByOrderId(orderFailedEvent.getId())
                                           .map(this::reversePayment)
                                           .map(p -> p.withOrderId(orderFailedEvent.getId()))
                                           .map(p -> this.saveAndNotify(p, getEventName(p)))
                                           .orElseThrow(() -> new IllegalStateException("payment not found"));
    }

    private Payment reversePayment(Payment payment) {
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

    public Payment createFundLock(String accountId, Long amountUnit) {
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

    private void onOrderCompleted(OrderEvent orderCreatedEvent) {
        Payment payment = paymentRepository.getPaymentByOrderId(orderCreatedEvent.getId())
                                           .map(this::executePayment)
                                           .map(p -> p.withOrderId(orderCreatedEvent.getId()))
                                           .map(p -> this.saveAndNotify(p, getEventName(p)))
                                           .orElseThrow(() -> new IllegalStateException("payment not found"));
    }

    private Payment executePayment(Payment payment) {
        removeFundLok(payment);
        return payment.withStatus("executed");
    }

    private static String getEventName(Payment payment) {
        return String.format("payment-%s", payment.getStatus());
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

}
