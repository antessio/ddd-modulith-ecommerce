package antessio.dddmodulith.ecommerce.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import antessio.dddmodulith.ecommerce.common.SerializationService;
import antessio.dddmodulith.ecommerce.order.OrderEvent;

public class NotificationApplicationService implements OnOrderUpdated{

    private static final Logger LOG = LoggerFactory.getLogger(NotificationApplicationService.class);

    private void sendNotification(SerializationService serializationService, String orderNotificationRaw) {
        OrderEvent orderEvent = serializationService.deserialize(orderNotificationRaw, OrderEvent.class);
        LOG.info("sending notification to " + orderEvent.getUser() + ": order #" + orderEvent.getId() + " updated");
    }


    @Override
    public void sendNotification(String text) {
        LOG.info(text);
    }

}
