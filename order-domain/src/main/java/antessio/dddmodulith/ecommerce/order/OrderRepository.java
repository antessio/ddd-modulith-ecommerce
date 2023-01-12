package antessio.dddmodulith.ecommerce.order;

import java.util.Optional;

interface OrderRepository {

    Optional<Order> getOrder(String id);

    void save(Order order);

}
