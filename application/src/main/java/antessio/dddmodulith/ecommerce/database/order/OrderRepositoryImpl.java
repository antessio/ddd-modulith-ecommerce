package antessio.dddmodulith.ecommerce.database.order;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import antessio.dddmodulith.ecommerce.order.Order;
import antessio.dddmodulith.ecommerce.order.OrderRepository;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Optional<Order> getOrder(String id) {
        return orderJpaRepository.findById(id)
                                 .map(Order.class::cast);
    }

    @Override
    public void save(Order order) {
        orderJpaRepository.save(
                new OrderEntity(
                        order.getId(),
                        order.getShippingAddress(),
                        order.getItems()
                             .stream().map(
                                     i -> new ItemEntity(
                                             i.getProductId(),
                                             i.getPriceAmountUnit(),
                                             i.getDescription(),
                                             i.getQuantity(),
                                             order.getId()))
                             .collect(Collectors.toSet()),
                        order.getPaymentId(),
                        order.getShippingId(),
                        order.getShippingStatus(),
                        order.getUsername()
                )
        );
    }

}
