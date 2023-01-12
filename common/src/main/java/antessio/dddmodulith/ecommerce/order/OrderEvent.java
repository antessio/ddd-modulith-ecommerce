package antessio.dddmodulith.ecommerce.order;

import java.util.List;
import java.util.UUID;

public final class OrderEvent {
    private String id;
    private String shippingAddress;
    private List<OrderEventItem> items;
    private String paymentId;
    private String user;

    public OrderEvent() {
    }

    public OrderEvent(String id, String shippingAddress, List<OrderEventItem> items, String paymentId, String user) {
        this.id = id;
        this.shippingAddress = shippingAddress;
        this.items = items;
        this.paymentId = paymentId;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public List<OrderEventItem> getItems() {
        return items;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getUser() {
        return user;
    }

}
