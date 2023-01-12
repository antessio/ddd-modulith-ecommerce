package antessio.dddmodulith.ecommerce.database.shipping;

import java.util.Optional;

import org.springframework.stereotype.Component;

import antessio.dddmodulith.ecommerce.shipping.Shipping;
import antessio.dddmodulith.ecommerce.shipping.ShippingRepository;

@Component
public class ShippingRepositoryImpl implements ShippingRepository {

    private final ShippingJpaRepository shippingJpaRepository;

    public ShippingRepositoryImpl(ShippingJpaRepository shippingJpaRepository) {
        this.shippingJpaRepository = shippingJpaRepository;
    }

    @Override
    public void save(Shipping shipping) {
        shippingJpaRepository.save(
                new ShippingEntity(
                        shipping.getShippingId(),
                        shipping.getOrderId(),
                        shipping.getCourier(),
                        shipping.getStatus(),
                        shipping.getShippingAddress()
                )
        );
    }

    @Override
    public Optional<Shipping> loadShippingById(String id) {
        return shippingJpaRepository.findById(id)
                                    .map(Shipping.class::cast);
    }

}
