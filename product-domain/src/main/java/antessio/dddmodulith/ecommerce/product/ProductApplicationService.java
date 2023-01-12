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

public class ProductApplicationService implements ProductServiceInterface {

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
        messageBroker.subscribe(
                Subscriber.of(this.getClass().getCanonicalName(),
                              "order-created", orderCreatedRaw -> {
                            OrderEvent orderCreatedEvent = serializationService.deserialize(orderCreatedRaw, OrderEvent.class);
                            this.onOrderCreated(orderCreatedEvent);
                        }));
    }

    private void onOrderCreated(OrderEvent orderCreatedEvent) {
        Map<String, OrderEventItem> productsInOrder = orderCreatedEvent.getItems()
                                                                       .stream()
                                                                       .collect(Collectors.toMap(OrderEventItem::getProductId, o -> o));
        productService.loadByProductIds(new ArrayList<>(productsInOrder.keySet()))
                      .forEach(p -> Optional.ofNullable(productsInOrder.get(p.getProductId()))
                                            .ifPresent(i -> productService.updateStocks(p, i.getQuantity())));
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

}
