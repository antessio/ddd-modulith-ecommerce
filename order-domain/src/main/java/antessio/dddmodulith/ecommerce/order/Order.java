package antessio.dddmodulith.ecommerce.order;

import java.util.List;
import java.util.UUID;

public final class Order {
    private final String id;
    private final String shippingAddress;
    private final List<Item> items;
    private final String paymentId;

    private Order(String shippingAddress, List<Item> items, String paymentId) {
        this.id = UUID.randomUUID().toString();
        this.shippingAddress = shippingAddress;
        this.items = items;
        this.paymentId = paymentId;
    }

    public static Order of(String shippingAddress, List<Item> items, String paymentId) {
        return new Order(shippingAddress, items, paymentId);
    }

    public String getId() {
        return this.id;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public String getPaymentId() {
        return this.paymentId;
    }

    public Order withShippingAddress(String shippingAddress) {
        return of(shippingAddress, getItems(), getPaymentId());
    }

    public Order withItems(List<Item> items) {
        return of(getShippingAddress(), items, getPaymentId());
    }

    public Order withPaymentId(String paymentId) {
        return of(getShippingAddress(), getItems(), paymentId);
    }


}
