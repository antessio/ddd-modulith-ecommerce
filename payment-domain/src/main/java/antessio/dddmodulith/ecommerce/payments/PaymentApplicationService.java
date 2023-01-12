package antessio.dddmodulith.ecommerce.payments;

import antessio.dddmodulith.ecommerce.common.MessageBroker;
import antessio.dddmodulith.ecommerce.common.SerializationService;
import antessio.dddmodulith.ecommerce.common.Subscriber;
import antessio.dddmodulith.ecommerce.order.OrderEvent;
import antessio.dddmodulith.ecommerce.payment.PaymentServiceInterface;

public class PaymentApplicationService implements PaymentServiceInterface {

    private final PaymentService paymentService;

    public PaymentApplicationService(SerializationService serializationService, MessageBroker messageBroker, PaymentRepository paymentRepository) {
        this.paymentService = new PaymentService(serializationService, messageBroker, paymentRepository);
        messageBroker.subscribe(Subscriber.of(this.getClass().getCanonicalName(), "order-completed", orderCreated -> {
            OrderEvent orderCreatedEvent = serializationService.deserialize(orderCreated, OrderEvent.class);
            if (orderCreatedEvent.getPaymentId() != null) {
                this.onOrderCompleted(orderCreatedEvent);
            }
        }));
        messageBroker.subscribe(Subscriber.of(this.getClass().getCanonicalName(),
                                              "order-failed", orderFailed -> {
                    OrderEvent orderFailedEvent = serializationService.deserialize(orderFailed, OrderEvent.class);
                    this.onOrderFailed(orderFailedEvent);

                }));
    }

    @Override
    public String createFundLock(String accountId, Long amountUnit) {
        return paymentService.createFundLock(accountId, amountUnit).getId();
    }

    private void onOrderFailed(OrderEvent orderFailedEvent) {
        paymentService.loadById(orderFailedEvent.getPaymentId())
                      .map(p -> paymentService.updateOrderId(p, orderFailedEvent.getId()))
                      .ifPresent(paymentService::reversePayment);

    }

    private void onOrderCompleted(OrderEvent orderCreatedEvent) {
        paymentService.loadById(orderCreatedEvent.getPaymentId())
                      .map(p -> paymentService.updateOrderId(p, orderCreatedEvent.getId()))
                      .ifPresent(paymentService::executePayment);


    }

}
