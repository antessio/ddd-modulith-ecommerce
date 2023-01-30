package antessio.dddmodulith.ecommerce.messagebroker;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import antessio.dddmodulith.ecommerce.common.MessageBroker;
import antessio.dddmodulith.ecommerce.common.SerializationService;
import antessio.dddmodulith.ecommerce.common.Subscriber;
import antessio.dddmodulith.ecommerce.notification.OnOrderUpdated;
import antessio.dddmodulith.ecommerce.order.OrderEvent;

@Component
public class OrderNotificationEventListener {

    private final MessageBroker messageBroker;
    private final SerializationService serializationService;
    private final OnOrderUpdated onOrderUpdated;

    public OrderNotificationEventListener(MessageBroker messageBroker, SerializationService serializationService, OnOrderUpdated onOrderUpdated) {
        this.messageBroker = messageBroker;
        this.serializationService = serializationService;
        this.onOrderUpdated = onOrderUpdated;
    }

    @PostConstruct
    public void init() {
        messageBroker.subscribe(Subscriber.of(this.getClass().getCanonicalName(), "order-created", this::sendNotification));
        messageBroker.subscribe(Subscriber.of(this.getClass().getCanonicalName(), "order-completed", this::sendNotification));
        messageBroker.subscribe(Subscriber.of(this.getClass().getCanonicalName(), "shipping-updated", this::sendNotification));
    }

    private void sendNotification(String orderNotificationRaw) {
        OrderEvent orderEvent = serializationService.deserialize(orderNotificationRaw, OrderEvent.class);
        onOrderUpdated.sendNotification("sending notification to " + orderEvent.getUser() + ": order #" + orderEvent.getId() + " updated");
    }
}
