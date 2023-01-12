package antessio.dddmodulith.ecommerce.order;

import java.util.Optional;
import java.util.stream.Collectors;

import antessio.dddmodulith.ecommerce.common.DomainService;
import antessio.dddmodulith.ecommerce.common.MessageBroker;
import antessio.dddmodulith.ecommerce.common.SerializationService;

class OrderService extends DomainService<Order> {

    private final OrderRepository orderRepository;

    public OrderService(
            SerializationService serializationService,
            MessageBroker messageBroker,
            OrderRepository orderRepository) {
        super(serializationService, messageBroker);
        this.orderRepository = orderRepository;
    }

    public Optional<Order> getOrder(String id) {
        return orderRepository.getOrder(id);
    }

    public String createOrder(CreateOrderCommand command) {
        Order order = Order.of(
                command.getShippingAddress(),
                command.getItems()
                       .stream()
                       .map(i -> Item.of(
                               i.getProductId(),
                               i.getPriceAmountUnit(),
                               i.getDescription(),
                               i.getQuantity()))
                       .collect(Collectors.toList()),
                command.getPaymentId(),
                null,
                null,
                command.getUser());
        saveAndNotify(order, "order-created");
        return order.getId();
    }

    public void payOrder(PayOrderCommand command) {
        this.getOrder(command.getOrderId())
            .map(o -> o.withPaymentId(command.getPaymentId()))
            .map(o -> saveAndNotify(o, "order-payed"))
            .orElseThrow(() -> new IllegalArgumentException("order not found"));

    }

    public void updateShippingInfo(
            String orderId,
            String shippingId,
            String shippingStatus) {
        this.getOrder(orderId)
            .map(o -> o.withShippingId(shippingId).withShippingStatus(shippingStatus))
            .map(o -> saveAndNotify(o, getEventTypeFromShippingStatus(shippingStatus)))
            .orElseThrow(() -> new IllegalArgumentException("order not found"));

    }

    private static String getEventTypeFromShippingStatus(String shippingStats) {
        if (shippingStats.equals("delivered")) {
            return "order-completed";
        } else {
            return "shipping-updated";
        }
    }

    @Override
    protected Order save(Order obj) {
        orderRepository.save(obj);
        return obj;
    }

    @Override
    protected Object convertToEvent(Order obj) {
        return orderToEvent(obj);
    }

    private OrderEvent orderToEvent(Order order) {
        return new OrderEvent(
                order.getId(),
                order.getShippingAddress(),
                order.getItems()
                     .stream()
                     .map(i -> new OrderEventItem(i.getProductId(), i.getPriceAmountUnit(), i.getDescription(), i.getQuantity()))
                     .collect(Collectors.toList()),
                order.getPaymentId(),
                order.getUsername()
        );
    }


}
