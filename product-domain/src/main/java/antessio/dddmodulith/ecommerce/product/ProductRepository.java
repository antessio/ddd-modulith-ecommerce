package antessio.dddmodulith.ecommerce.product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface ProductRepository {
    Stream<Product> getProducts(String startingFrom);

    Optional<Product> getProductById(String id);

    List<Product> getProductsByIds(List<String> ids);

    void saveProduct(Product product);

}
