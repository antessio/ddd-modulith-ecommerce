package antessio.dddmodulith.ecommerce.shipping;

import java.util.UUID;

public class Shipping {
    private  String shippingId;
    private  String orderId;

    private  String courier;

    private  String status;

    private  String shippingAddress;

    public Shipping() {
    }

    private Shipping(String orderId, String courier, String status, String shippingAddress) {
        this.shippingId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.courier = courier;
        this.status = status;
        this.shippingAddress = shippingAddress;
    }
    private Shipping(String shippingId, String orderId, String courier, String status, String shippingAddress) {
        this.shippingId = shippingId;
        this.orderId = orderId;
        this.courier = courier;
        this.status = status;
        this.shippingAddress = shippingAddress;
    }

    static Shipping of(String orderId, String courier, String status, String shippingAddress) {
        return new Shipping(orderId, courier, status, shippingAddress);
    }

    private static Shipping of(String shippingId, String orderId, String courier, String status, String shippingAddress) {
        return new Shipping(shippingId, orderId, courier, status, shippingAddress);
    }

    public String getShippingId() {
        return this.shippingId;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getCourier() {
        return this.courier;
    }

    public String getStatus() {
        return this.status;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public Shipping withOrderId(String orderId) {
        return of(getShippingId(), orderId, getCourier(), getStatus(), getShippingAddress());
    }

    public Shipping withCourier(String courier) {
        return of(getShippingId(), getOrderId(), courier, getStatus(), getShippingAddress());
    }

    public Shipping withStatus(String status) {
        return of(getShippingId(), getOrderId(), getCourier(), status, getShippingAddress());
    }

    public Shipping withShippingAddress(String shippingAddress) {
        return of(getShippingId(), getOrderId(), getCourier(), getStatus(), shippingAddress);
    }

}
