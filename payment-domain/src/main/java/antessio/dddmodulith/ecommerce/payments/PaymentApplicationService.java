package antessio.dddmodulith.ecommerce.payments;

import antessio.dddmodulith.ecommerce.common.MessageBroker;
import antessio.dddmodulith.ecommerce.common.SerializationService;
import antessio.dddmodulith.ecommerce.common.Subscriber;
import antessio.dddmodulith.ecommerce.order.OrderEvent;
import antessio.dddmodulith.ecommerce.payment.PaymentServiceInterface;

public class PaymentApplicationService implements PaymentServiceInterface, OnOrderCompleted, OnOrderFailed {

    private final PaymentService paymentService;

    public PaymentApplicationService(SerializationService serializationService,
                                     MessageBroker messageBroker,
                                     PaymentRepository paymentRepository) {
        this.paymentService = new PaymentService(serializationService, messageBroker, paymentRepository);
    }

    @Override
    public String createFundLock(String accountId, Long amountUnit) {
        return paymentService.createFundLock(accountId, amountUnit).getId();
    }



    @Override
    public void confirmPayment(PaymentConfirmationForOrder paymentConfirmationForOrder) {
        paymentService.loadById(paymentConfirmationForOrder.getPaymentId())
                      .map(p -> paymentService.updateOrderId(p, paymentConfirmationForOrder.getOrderId()))
                      .ifPresent(paymentService::executePayment);
    }

    @Override
    public void reversePayment(PaymentReverseForOrder paymentConfirmationForOrder) {
        paymentService.loadById(paymentConfirmationForOrder.getPaymentId())
                      .map(p -> paymentService.updateOrderId(p, paymentConfirmationForOrder.getOrderId()))
                      .ifPresent(paymentService::reversePayment);
    }

}
