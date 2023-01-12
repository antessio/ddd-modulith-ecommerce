package antessio.dddmodulith.ecommerce.order;

import java.util.List;
import java.util.UUID;

public class Order {
    private  String id;
    private  String shippingAddress;
    private  List<Item> items;
    private  String paymentId;
    private  String shippingId;
    private  String shippingStatus;
    private  String username;

    private Order(String shippingAddress, List<Item> items, String paymentId, String shippingId, String shippingStatus, String username) {
        this.id = UUID.randomUUID().toString();
        this.shippingAddress = shippingAddress;
        this.items = items;
        this.paymentId = paymentId;
        this.shippingId = shippingId;
        this.shippingStatus = shippingStatus;
        this.username = username;
    }
    private Order(String id, String shippingAddress, List<Item> items, String paymentId, String shippingId, String shippingStatus, String username) {
        this.id = id;
        this.shippingAddress = shippingAddress;
        this.items = items;
        this.paymentId = paymentId;
        this.shippingId = shippingId;
        this.shippingStatus = shippingStatus;
        this.username = username;
    }

    public Order() {
        
    }

    public static Order of(String shippingAddress, List<Item> items, String paymentId, String shippingId, String shippingStatus, String user) {
        return new Order(shippingAddress, items, paymentId, shippingId, shippingStatus, user);
    }

    static Order of(String id, String shippingAddress, List<Item> items, String paymentId, String shippingId, String shippingStatus, String user) {
        return new Order(id,shippingAddress, items, paymentId, shippingId, shippingStatus, user);
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

    public String getShippingId() {
        return this.shippingId;
    }

    public String getShippingStatus() {
        return this.shippingStatus;
    }

    public String getUsername() {
        return this.username;
    }

    public Order withShippingAddress(String shippingAddress) {
        return of(getId(), shippingAddress, getItems(), getPaymentId(), getShippingId(), getShippingStatus(), getUsername());
    }

    public Order withItems(List<Item> items) {
        return of(getId(), getShippingAddress(), items, getPaymentId(), getShippingId(), getShippingStatus(), getUsername());
    }

    public Order withPaymentId(String paymentId) {
        return of(getId(), getShippingAddress(), getItems(), paymentId, getShippingId(), getShippingStatus(), getUsername());
    }

    public Order withShippingId(String shippingId) {
        return of(getId(), getShippingAddress(), getItems(), getPaymentId(), shippingId, getShippingStatus(), getUsername());
    }

    public Order withShippingStatus(String shippingStatus) {
        return of(getId(), getShippingAddress(), getItems(), getPaymentId(), getShippingId(), shippingStatus, getUsername());
    }

    public Order withUser(String user) {
        return of(getId(), getShippingAddress(), getItems(), getPaymentId(), getShippingId(), getShippingStatus(), user);
    }

}
