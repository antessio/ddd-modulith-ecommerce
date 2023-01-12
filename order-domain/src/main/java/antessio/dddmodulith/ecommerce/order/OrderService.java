package antessio.dddmodulith.ecommerce.order;

import java.util.Optional;
import java.util.stream.Collectors;

import antessio.dddmodulith.ecommerce.common.CommonService;
import antessio.dddmodulith.ecommerce.common.MessageBroker;
import antessio.dddmodulith.ecommerce.common.SerializationService;

public class OrderService extends CommonService<Order> {

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
                command.getItems(),
                command.getPaymentId());
        saveAndNotify(order, "order-created");
        return order.getId();
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
        return OrderEvent.of(
                order.getId(),
                order.getShippingAddress(),
                order.getItems()
                     .stream()
                     .map(i -> OrderEventItem.of(i.getProductId(), i.getPriceAmountUnit(), i.getDescription()))
                     .collect(Collectors.toList()),
                order.getPaymentId());
    }


}
