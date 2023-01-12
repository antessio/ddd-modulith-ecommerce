package antessio.dddmodulith.ecommerce.order;

import java.util.List;

public final class OrderDTO {
    private final String id;
    private final String shippingAddress;
    private final List<OrderItemDTO> items;
    private final String paymentId;
    private final String shippingId;
    private final String shippingStatus;
    private final String user;

    private OrderDTO(String id, String shippingAddress, List<OrderItemDTO> items, String paymentId, String shippingId, String shippingStatus, String user) {
        this.id = id;
        this.shippingAddress = shippingAddress;
        this.items = items;
        this.paymentId = paymentId;
        this.shippingId = shippingId;
        this.shippingStatus = shippingStatus;
        this.user = user;
    }

    public static OrderDTO of(
            String id,
            String shippingAddress,
            List<OrderItemDTO> items,
            String paymentId,
            String shippingId,
            String shippingStatus,
            String user) {
        return new OrderDTO(id, shippingAddress, items, paymentId, shippingId, shippingStatus, user);
    }

    public String getId() {
        return this.id;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public List<OrderItemDTO> getItems() {
        return this.items;
    }

    public String getPaymentId() {
        return this.paymentId;
    }

    public String getShippingId() {
        return this.shippingId;
    }

    public String getShippingStatus() {
        return this.shippingStatus;
    }

    public String getUser() {
        return this.user;
    }

    public OrderDTO withId(String id) {
        return of(id, getShippingAddress(), getItems(), getPaymentId(), getShippingId(), getShippingStatus(), getUser());
    }

    public OrderDTO withShippingAddress(String shippingAddress) {
        return of(getId(), shippingAddress, getItems(), getPaymentId(), getShippingId(), getShippingStatus(), getUser());
    }

    public OrderDTO withItems(List<OrderItemDTO> items) {
        return of(getId(), getShippingAddress(), items, getPaymentId(), getShippingId(), getShippingStatus(), getUser());
    }

    public OrderDTO withPaymentId(String paymentId) {
        return of(getId(), getShippingAddress(), getItems(), paymentId, getShippingId(), getShippingStatus(), getUser());
    }

    public OrderDTO withShippingId(String shippingId) {
        return of(getId(), getShippingAddress(), getItems(), getPaymentId(), shippingId, getShippingStatus(), getUser());
    }

    public OrderDTO withShippingStatus(String shippingStatus) {
        return of(getId(), getShippingAddress(), getItems(), getPaymentId(), getShippingId(), shippingStatus, getUser());
    }

    public OrderDTO withUser(String user) {
        return of(getId(), getShippingAddress(), getItems(), getPaymentId(), getShippingId(), getShippingStatus(), user);
    }

}
