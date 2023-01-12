package antessio.dddmodulith.ecommerce.api.payment;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import antessio.dddmodulith.ecommerce.product.CreateProductCommand;
import antessio.dddmodulith.ecommerce.product.ProductApplicationService;

@Service
public class ProductCatalogInitializer {
    private static final Logger LOG = LoggerFactory.getLogger(ProductCatalogInitializer.class);

    private final ProductApplicationService productApplicationService;

    public ProductCatalogInitializer(ProductApplicationService productApplicationService) {
        this.productApplicationService = productApplicationService;
    }

    @PostConstruct
    public void init() {
        Random random = new Random();
        IntStream.range(1, 200)
                .mapToObj(i-> CreateProductCommand.of(
                        random.nextLong(0,9999999),
                        "Product description  "+i,
                        random.nextLong(0,9999),
                        List.of(i + "_image1", i + "_image2", i + "_image3")
                ))
                .peek(p->LOG.info("Creating product "+p.getDescription()))
                .forEach(productApplicationService::createProduct);
    }

}
