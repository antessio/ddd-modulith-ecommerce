package antessio.dddmodulith.ecommerce.shipping;

import java.util.Optional;

public interface ShippingRepository {
    void save(Shipping shipping);

    Optional<Shipping> loadShippingById(String id);

}
