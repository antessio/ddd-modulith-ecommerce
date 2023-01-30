package antessio.dddmodulith.ecommerce.messagebroker;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import antessio.dddmodulith.ecommerce.common.MessageBroker;
import antessio.dddmodulith.ecommerce.common.SerializationService;
import antessio.dddmodulith.ecommerce.common.Subscriber;
import antessio.dddmodulith.ecommerce.order.OnShippingUpdated;
import antessio.dddmodulith.ecommerce.order.UpdateShippingInfoCommand;
import antessio.dddmodulith.ecommerce.shipping.ShippingEvent;

@Component
public class ShippingOrderEventListener {

    private final MessageBroker messageBroker;
    private final SerializationService serializationService;
    private final OnShippingUpdated onShippingUpdated;

    public ShippingOrderEventListener(MessageBroker messageBroker, SerializationService serializationService, OnShippingUpdated onShippingUpdated) {
        this.messageBroker = messageBroker;
        this.serializationService = serializationService;
        this.onShippingUpdated = onShippingUpdated;
    }

    @PostConstruct
    public void init() {
        messageBroker.subscribe(Subscriber.of(this.getClass().getCanonicalName(), "shipping-preparing", shippingRawEvent -> {
            ShippingEvent shippingEvent = serializationService.deserialize(shippingRawEvent, ShippingEvent.class);
            this.onShippingEvent(shippingEvent);
        }));
        messageBroker.subscribe(Subscriber.of(this.getClass().getCanonicalName(), "shipping-in-delivery", shippingRawEvent -> {
            ShippingEvent shippingEvent = serializationService.deserialize(shippingRawEvent, ShippingEvent.class);
            this.onShippingEvent(shippingEvent);
        }));

        messageBroker.subscribe(Subscriber.of(this.getClass().getCanonicalName(), "shipping-delivered", shippingRawEvent -> {
            ShippingEvent shippingEvent = serializationService.deserialize(shippingRawEvent, ShippingEvent.class);
            this.onShippingEvent(shippingEvent);
        }));
    }

    private void onShippingEvent(ShippingEvent shippingEvent) {
        onShippingUpdated.updateShippingInfo(UpdateShippingInfoCommand.of(
                shippingEvent.getOrderId(),
                shippingEvent.getShippingId(),
                shippingEvent.getStatus()));
    }

}
