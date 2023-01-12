package antessio.dddmodulith.ecommerce.database.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import antessio.dddmodulith.ecommerce.database.product.ProductEntity;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, String>{



}
