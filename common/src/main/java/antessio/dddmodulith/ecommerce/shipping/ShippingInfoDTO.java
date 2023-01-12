package antessio.dddmodulith.ecommerce.shipping;

public class ShippingInfoDTO {
    private  String shippingId;
    private  String orderId;

    private  String courier;

    private  String status;

    private  String shippingAddress;

    public ShippingInfoDTO(String shippingId, String orderId, String courier, String status, String shippingAddress) {
        this.shippingId = shippingId;
        this.orderId = orderId;
        this.courier = courier;
        this.status = status;
        this.shippingAddress = shippingAddress;
    }

    public ShippingInfoDTO() {
    }

    public String getShippingId() {
        return shippingId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCourier() {
        return courier;
    }

    public String getStatus() {
        return status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

}
