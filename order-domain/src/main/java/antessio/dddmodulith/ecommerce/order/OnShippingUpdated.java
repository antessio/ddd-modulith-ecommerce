package antessio.dddmodulith.ecommerce.order;

public interface OnShippingUpdated {

    void updateShippingInfo(UpdateShippingInfoCommand command);

}
