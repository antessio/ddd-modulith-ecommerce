package antessio.dddmodulith.ecommerce.order;

public interface OrderServiceInterface {
    String createOrder(CreateOrderCommand command);

    void payOrder(PayOrderCommand command);

    OrderDTO getOrder(String id);

}
