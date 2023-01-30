package antessio.dddmodulith.ecommerce.shipping;

public final class CreateShippingCommand {
    private final String orderId;
    private final String courier;
    private final String shippingAddress;


    private CreateShippingCommand(String orderId, String courier, String shippingAddress) {
        this.orderId = orderId;
        this.courier = courier;
        this.shippingAddress = shippingAddress;
    }

    public static CreateShippingCommand of(String orderId, String courier, String shippingAddress) {
        return new CreateShippingCommand(orderId, courier, shippingAddress);
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getCourier() {
        return this.courier;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public CreateShippingCommand withOrderId(String orderId) {
        return of(orderId, getCourier(), getShippingAddress());
    }

    public CreateShippingCommand withCourier(String courier) {
        return of(getOrderId(), courier, getShippingAddress());
    }

    public CreateShippingCommand withShippingAddress(String shippingAddress) {
        return of(getOrderId(), getCourier(), shippingAddress);
    }

}
