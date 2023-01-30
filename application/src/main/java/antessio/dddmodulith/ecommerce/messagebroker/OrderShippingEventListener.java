package antessio.dddmodulith.ecommerce.messagebroker;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import antessio.dddmodulith.ecommerce.common.MessageBroker;
import antessio.dddmodulith.ecommerce.common.SerializationService;
import antessio.dddmodulith.ecommerce.common.Subscriber;
import antessio.dddmodulith.ecommerce.order.OrderEvent;
import antessio.dddmodulith.ecommerce.shipping.CreateShippingCommand;
import antessio.dddmodulith.ecommerce.shipping.OnOrderCreated;

@Component
public class OrderShippingEventListener {

    private final MessageBroker messageBroker;
    private final SerializationService serializationService;
    private final OnOrderCreated onOrderCreated;

    public OrderShippingEventListener(MessageBroker messageBroker, SerializationService serializationService, OnOrderCreated onOrderCreated) {
        this.messageBroker = messageBroker;
        this.serializationService = serializationService;
        this.onOrderCreated = onOrderCreated;
    }

    @PostConstruct
    public void init() {
        messageBroker.subscribe(Subscriber.of(this.getClass().getCanonicalName(),
                                              "order-created", orderCreatedRaw -> {
                    OrderEvent orderCreatedEvent = serializationService.deserialize(orderCreatedRaw, OrderEvent.class);
                    onOrderCreated.createShipping(CreateShippingCommand.of(
                            orderCreatedEvent.getId(),
                            "courier-1",
                            orderCreatedEvent.getShippingAddress()
                    ));
                }));
    }

}
