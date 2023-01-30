package antessio.dddmodulith.ecommerce.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import antessio.dddmodulith.ecommerce.common.MessageBroker;
import antessio.dddmodulith.ecommerce.common.SerializationService;
import antessio.dddmodulith.ecommerce.common.Subscriber;
import antessio.dddmodulith.ecommerce.order.OrderEvent;
import antessio.dddmodulith.ecommerce.order.OrderEventItem;

public class ProductApplicationService implements ProductServiceInterface, OnOrderCreated {

    private final ProductService productService;


    public ProductApplicationService(
            SerializationService serializationService,
            MessageBroker messageBroker,
            ProductRepository repository) {
        this.productService = new ProductService(
                serializationService,
                messageBroker,
                repository
        );

    }


    @Override
    public List<ProductDTO> getProducts() {
        return productService.loadAllProducts()
                             .map(p -> ProductDTO.of(
                                     p.getProductId(),
                                     p.getPriceAmountUnit(),
                                     p.getDescription(),
                                     p.getStocks(),
                                     p.getRate(),
                                     p.getImages()
                             ))
                             .collect(Collectors.toList());
    }

    @Override
    public void createProduct(CreateProductCommand createProductCommand) {
        productService.createProduct(Product.of(
                createProductCommand.getPriceAmountUnit(),
                createProductCommand.getDescription(),
                createProductCommand.getStocks(),
                null,
                createProductCommand.getImages()
        ));
    }

    @Override
    public void updateStocks(UpdateStocksCommand updateStocksCommand) {
        productService.loadByProductIds(new ArrayList<>(updateStocksCommand.getProductIdQuantity().keySet()))
                      .forEach(p -> Optional.ofNullable(updateStocksCommand.getProductIdQuantity().get(p.getProductId()))
                                            .ifPresent(i -> productService.updateStocks(p, i)));
    }

}
