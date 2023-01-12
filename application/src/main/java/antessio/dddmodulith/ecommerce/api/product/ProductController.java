package antessio.dddmodulith.ecommerce.api.product;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import antessio.dddmodulith.ecommerce.product.ProductApplicationService;
import antessio.dddmodulith.ecommerce.product.ProductDTO;

@RestController
public class ProductController {

    private final ProductApplicationService productApplicationService;

    public ProductController(ProductApplicationService productApplicationService) {
        this.productApplicationService = productApplicationService;
    }

    @GetMapping("/products")
    public List<ProductDTO> getProducts(){
        return productApplicationService.getProducts();
    }

}
