package antessio.dddmodulith.ecommerce.order;

import java.util.Optional;

public interface OrderRepository {

    Optional<Order> getOrder(String id);

    void save(Order order);

}
