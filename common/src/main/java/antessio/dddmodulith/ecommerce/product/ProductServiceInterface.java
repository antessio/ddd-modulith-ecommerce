package antessio.dddmodulith.ecommerce.product;

import java.util.List;

public interface ProductServiceInterface{

    List<ProductDTO> getProducts();

    void createProduct(CreateProductCommand createProductCommand);

}
