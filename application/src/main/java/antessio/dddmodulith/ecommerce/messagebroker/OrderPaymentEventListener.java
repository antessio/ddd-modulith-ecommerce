package antessio.dddmodulith.ecommerce.messagebroker;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import antessio.dddmodulith.ecommerce.common.MessageBroker;
import antessio.dddmodulith.ecommerce.common.SerializationService;
import antessio.dddmodulith.ecommerce.common.Subscriber;
import antessio.dddmodulith.ecommerce.order.OrderEvent;
import antessio.dddmodulith.ecommerce.payments.OnOrderCompleted;
import antessio.dddmodulith.ecommerce.payments.OnOrderFailed;
import antessio.dddmodulith.ecommerce.payments.PaymentConfirmationForOrder;
import antessio.dddmodulith.ecommerce.payments.PaymentReverseForOrder;

@Component
public class OrderPaymentEventListener {

    private final MessageBroker messageBroker;
    private final SerializationService serializationService;
    private final OnOrderCompleted onOrderCompleted;
    private final OnOrderFailed onOrderFailed;

    public OrderPaymentEventListener(
            MessageBroker messageBroker,
            SerializationService serializationService,
            OnOrderCompleted onOrderCompleted,
            OnOrderFailed onOrderFailed) {
        this.messageBroker = messageBroker;
        this.serializationService = serializationService;
        this.onOrderCompleted = onOrderCompleted;
        this.onOrderFailed = onOrderFailed;
    }

    @PostConstruct
    public void init() {
        messageBroker.subscribe(Subscriber.of(this.getClass().getCanonicalName(), "order-completed", this::onOrderCompleted));
        messageBroker.subscribe(Subscriber.of(this.getClass().getCanonicalName(), "order-failed", this::onOrderFailed));
    }

    private void onOrderFailed(String orderFailed) {
        OrderEvent orderFailedEvent = serializationService.deserialize(orderFailed, OrderEvent.class);
        onOrderFailed.reversePayment(PaymentReverseForOrder.of(orderFailedEvent.getId(), orderFailedEvent.getPaymentId()));
    }

    private void onOrderCompleted(String orderCreated) {
        OrderEvent orderCreatedEvent = serializationService.deserialize(orderCreated, OrderEvent.class);
        if (orderCreatedEvent.getPaymentId() != null) {
            onOrderCompleted.confirmPayment(PaymentConfirmationForOrder.of(orderCreatedEvent.getId(), orderCreatedEvent.getPaymentId()));
        }
    }


}
