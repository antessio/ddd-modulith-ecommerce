package antessio.dddmodulith.ecommerce.order;

public final class UpdateShippingInfoCommand {
    private final String orderId;
    private final String shippingId;
    private final String shippingStatus;

    private UpdateShippingInfoCommand(String orderId, String shippingId, String shippingStatus) {
        this.orderId = orderId;
        this.shippingId = shippingId;
        this.shippingStatus = shippingStatus;
    }

    public static UpdateShippingInfoCommand of(String orderId, String shippingId, String shippingStatus) {
        return new UpdateShippingInfoCommand(orderId, shippingId, shippingStatus);
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getShippingId() {
        return this.shippingId;
    }

    public String getShippingStatus() {
        return this.shippingStatus;
    }

    public UpdateShippingInfoCommand withOrderId(String orderId) {
        return of(orderId, getShippingId(), getShippingStatus());
    }

    public UpdateShippingInfoCommand withShippingId(String shippingId) {
        return of(getOrderId(), shippingId, getShippingStatus());
    }

    public UpdateShippingInfoCommand withShippingStatus(String shippingStatus) {
        return of(getOrderId(), getShippingId(), shippingStatus);
    }

}
