package antessio.dddmodulith.ecommerce.order;

import java.util.stream.Collectors;

import antessio.dddmodulith.ecommerce.common.MessageBroker;
import antessio.dddmodulith.ecommerce.common.SerializationService;
import antessio.dddmodulith.ecommerce.common.Subscriber;
import antessio.dddmodulith.ecommerce.shipping.ShippingEvent;

public class OrderApplicationService implements OrderServiceInterface {


    private final OrderService orderService;

    public OrderApplicationService(SerializationService serializationService, MessageBroker messageBroker, OrderRepository orderRepository) {
        this.orderService = new OrderService(serializationService, messageBroker, orderRepository);
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
        orderService.updateShippingInfo(
                shippingEvent.getOrderId(),
                shippingEvent.getShippingId(),
                shippingEvent.getStatus());
    }

    @Override
    public String createOrder(CreateOrderCommand command) {
        return this.orderService.createOrder(command);
    }

    @Override
    public void payOrder(PayOrderCommand command) {
        this.orderService.payOrder(command);
    }

    @Override
    public OrderDTO getOrder(String id) {
        return this.orderService.getOrder(id)
                                .map(o -> OrderDTO.of(
                                        o.getId(),
                                        o.getShippingAddress(),
                                        o.getItems().stream()
                                         .map(i -> OrderItemDTO.of(
                                                 i.getProductId(),
                                                 i.getPriceAmountUnit(),
                                                 i.getDescription(),
                                                 i.getQuantity()
                                         )).collect(Collectors.toList()),
                                        o.getPaymentId(),
                                        o.getShippingId(),
                                        o.getShippingStatus(),
                                        o.getUsername()
                                ))
                                .orElseThrow(() -> new IllegalArgumentException("order not found"));
    }

}
