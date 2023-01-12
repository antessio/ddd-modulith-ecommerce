package antessio.dddmodulith.ecommerce.shipping;

import java.util.Optional;

import antessio.dddmodulith.ecommerce.common.DomainService;
import antessio.dddmodulith.ecommerce.common.MessageBroker;
import antessio.dddmodulith.ecommerce.common.SerializationService;

class ShippingService extends DomainService<Shipping> {

    private final ShippingRepository repository;

    public ShippingService(
            SerializationService serializationService,
            MessageBroker messageBroker,
            ShippingRepository repository) {
        super(serializationService, messageBroker);
        this.repository = repository;
    }

    public void startDelivery(String shippingId) {
        updateStatus(shippingId, "in-delivery");
    }

    public void completeDelivery(String shippingId) {
        updateStatus(shippingId, "delivered");
    }

    private void updateStatus(String shippingId, String status) {
        Shipping shipping = repository.loadShippingById(shippingId)
                                      .orElseThrow(() -> new IllegalArgumentException("shipping not found"));
        shipping = shipping.withStatus(status);
        saveAndNotify(shipping, getEventType(shipping));
    }

    private String getEventType(Shipping shipping) {
        return String.format("shipping-%s", shipping.getStatus());
    }

    @Override
    protected Shipping save(Shipping obj) {
        repository.save(obj);
        return obj;
    }

    @Override
    protected Object convertToEvent(Shipping obj) {
        return new ShippingEvent(
                obj.getShippingId(),
                obj.getOrderId(),
                obj.getCourier(),
                obj.getStatus(),
                obj.getShippingAddress()
        );
    }

    public void create(Shipping shipping) {
        this.saveAndNotify(shipping, getEventType(shipping));
    }

    public Optional<Shipping> loadById(String id) {
        return repository.loadShippingById(id);
    }

}
