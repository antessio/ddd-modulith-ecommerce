package antessio.dddmodulith.ecommerce.shipping;

public interface ShippingServiceInterface {
    void startDelivery(String shippingId);

    void completeDelivery(String shippingId);

    ShippingInfoDTO getShippingInfo(String id);

}
