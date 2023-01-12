package antessio.dddmodulith.ecommerce.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import antessio.dddmodulith.ecommerce.common.MessageBroker;
import antessio.dddmodulith.ecommerce.common.SerializationService;
import antessio.dddmodulith.ecommerce.common.Subscriber;
import antessio.dddmodulith.ecommerce.order.OrderEvent;

public class NotificationApplicationService {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationApplicationService.class);

    public NotificationApplicationService(
            SerializationService serializationService,
            MessageBroker messageBroker) {
        messageBroker.subscribe(Subscriber.of(this.getClass().getCanonicalName(), "order-created", o -> this.sendNotification(serializationService, o)));
        messageBroker.subscribe(Subscriber.of(this.getClass().getCanonicalName(), "order-completed", o -> this.sendNotification(serializationService, o)));
        messageBroker.subscribe(Subscriber.of(this.getClass().getCanonicalName(), "shipping-updated", o -> this.sendNotification(serializationService, o)));
    }

    private void sendNotification(SerializationService serializationService, String orderNotificationRaw) {
        OrderEvent orderEvent = serializationService.deserialize(orderNotificationRaw, OrderEvent.class);
        LOG.info("sending notification to " + orderEvent.getUser() + ": order #" + orderEvent.getId() + " updated");
    }


}
