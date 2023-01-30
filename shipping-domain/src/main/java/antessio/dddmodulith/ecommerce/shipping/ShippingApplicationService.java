package antessio.dddmodulith.ecommerce.shipping;

import antessio.dddmodulith.ecommerce.common.MessageBroker;
import antessio.dddmodulith.ecommerce.common.SerializationService;

public class ShippingApplicationService implements ShippingServiceInterface, OnOrderCreated {

    private ShippingService shippingService;

    public ShippingApplicationService(
            SerializationService serializationService,
            MessageBroker messageBroker,
            ShippingRepository repository) {
        shippingService = new ShippingService(
                serializationService,
                messageBroker,
                repository);


    }

    @Override
    public void startDelivery(String shippingId) {
        this.shippingService.startDelivery(shippingId);
    }

    @Override
    public void completeDelivery(String shippingId) {
        this.shippingService.completeDelivery(shippingId);
    }

    @Override
    public ShippingInfoDTO getShippingInfo(String id) {
        return this.shippingService.loadById(id)
                                   .map(s -> new ShippingInfoDTO(
                                           s.getShippingId(),
                                           s.getOrderId(),
                                           s.getCourier(),
                                           s.getStatus(),
                                           s.getShippingAddress()))
                                   .orElseThrow(() -> new IllegalArgumentException("shipping not found"));

    }



    @Override
    public void createShipping(CreateShippingCommand command) {
        Shipping shipping = Shipping.of(
                command.getOrderId(),
                command.getCourier(),
                "preparing",
                command.getShippingAddress()
        );
        this.shippingService.create(shipping);
    }

}
