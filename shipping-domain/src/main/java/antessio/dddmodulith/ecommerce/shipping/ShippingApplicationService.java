package antessio.dddmodulith.ecommerce.shipping;

import antessio.dddmodulith.ecommerce.common.MessageBroker;
import antessio.dddmodulith.ecommerce.common.SerializationService;
import antessio.dddmodulith.ecommerce.common.Subscriber;
import antessio.dddmodulith.ecommerce.order.OrderEvent;

public class ShippingApplicationService implements ShippingServiceInterface {

    private ShippingService shippingService;

    public ShippingApplicationService(
            SerializationService serializationService,
            MessageBroker messageBroker,
            ShippingRepository repository) {
        shippingService = new ShippingService(
                serializationService,
                messageBroker,
                repository);

        messageBroker.subscribe(Subscriber.of(this.getClass().getCanonicalName(),
                                              "order-created", orderCreatedRaw -> {
                    OrderEvent orderCreatedEvent = serializationService.deserialize(orderCreatedRaw, OrderEvent.class);
                    this.onOrderCreated(orderCreatedEvent);
                }));
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

    private void onOrderCreated(OrderEvent orderCreatedEvent) {
        Shipping shipping = Shipping.of(
                orderCreatedEvent.getId(),
                "courier-1",
                "preparing",
                orderCreatedEvent.getShippingAddress()
        );
        this.shippingService.create(shipping);
    }

}
