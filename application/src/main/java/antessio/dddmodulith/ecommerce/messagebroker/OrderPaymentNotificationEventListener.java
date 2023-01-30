package antessio.dddmodulith.ecommerce.messagebroker;

import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import antessio.dddmodulith.ecommerce.common.MessageBroker;
import antessio.dddmodulith.ecommerce.common.SerializationService;
import antessio.dddmodulith.ecommerce.common.Subscriber;
import antessio.dddmodulith.ecommerce.order.OrderEvent;
import antessio.dddmodulith.ecommerce.order.OrderEventItem;
import antessio.dddmodulith.ecommerce.product.OnOrderCreated;
import antessio.dddmodulith.ecommerce.product.UpdateStocksCommand;

@Component
public class OrderPaymentNotificationEventListener {
    private final MessageBroker messageBroker;
    private final SerializationService serializationService;

    private final OnOrderCreated onOrderCreated;

    public OrderPaymentNotificationEventListener(MessageBroker messageBroker, SerializationService serializationService, OnOrderCreated onOrderCreated) {
        this.messageBroker = messageBroker;
        this.serializationService = serializationService;
        this.onOrderCreated = onOrderCreated;
    }

    @PostConstruct
    public void init(){
        messageBroker.subscribe(
                Subscriber.of(this.getClass().getCanonicalName(),
                              "order-created", orderCreatedRaw -> {
                            OrderEvent orderCreatedEvent = serializationService.deserialize(orderCreatedRaw, OrderEvent.class);
                            this.onOrderCreated(orderCreatedEvent);
                        }));
    }

    private void onOrderCreated(OrderEvent orderCreatedEvent) {
        Map<String, Integer> productsInOrder = orderCreatedEvent.getItems()
                                                                       .stream()
                                                                       .collect(Collectors.toMap(OrderEventItem::getProductId,
                                                                                                 OrderEventItem::getQuantity));
        onOrderCreated.updateStocks(new UpdateStocksCommand(productsInOrder));
    }

}
