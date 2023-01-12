package antessio.dddmodulith.ecommerce.order;

import java.util.List;
import java.util.UUID;

public final class OrderEvent {
    private final String id;
    private final String shippingAddress;
    private final List<OrderEventItem> items;

    private final String paymentId;

    private OrderEvent(String id, String shippingAddress, List<OrderEventItem> items, String paymentId) {
        this.id = id;
        this.shippingAddress = shippingAddress;
        this.items = items;
        this.paymentId = paymentId;
    }

    public static OrderEvent of(String id, String shippingAddress, List<OrderEventItem> items, String paymentId) {
        return new OrderEvent(id, shippingAddress, items, paymentId);
    }

    public String getId() {
        return this.id;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public List<OrderEventItem> getItems() {
        return this.items;
    }

    public String getPaymentId() {
        return this.paymentId;
    }

    public OrderEvent withId(String id) {
        return of(id, getShippingAddress(), getItems(), getPaymentId());
    }

    public OrderEvent withShippingAddress(String shippingAddress) {
        return of(getId(), shippingAddress, getItems(), getPaymentId());
    }

    public OrderEvent withItems(List<OrderEventItem> items) {
        return of(getId(), getShippingAddress(), items, getPaymentId());
    }

    public OrderEvent withPaymentId(String paymentId) {
        return of(getId(), getShippingAddress(), getItems(), paymentId);
    }


}
