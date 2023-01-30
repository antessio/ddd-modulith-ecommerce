package antessio.dddmodulith.ecommerce.shipping;

public interface OnOrderCreated {

    void createShipping(CreateShippingCommand command);
}
