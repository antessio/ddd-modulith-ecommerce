package antessio.dddmodulith.ecommerce.product;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import antessio.dddmodulith.ecommerce.common.DomainService;
import antessio.dddmodulith.ecommerce.common.MessageBroker;
import antessio.dddmodulith.ecommerce.common.SerializationService;

class ProductService extends DomainService<Product> {

    private final ProductRepository repository;

    public ProductService(
            SerializationService serializationService,
            MessageBroker messageBroker,
            ProductRepository repository) {
        super(serializationService, messageBroker);
        this.repository = repository;
    }


    @Override
    protected Product save(Product obj) {
        repository.saveProduct(obj);
        return obj;
    }

    @Override
    protected Object convertToEvent(Product obj) {
        return new ProductEvent(
                obj.getProductId(),
                obj.getPriceAmountUnit(),
                obj.getDescription(),
                obj.getStocks(),
                obj.getRate(),
                obj.getImages()
        );
    }

    public List<Product> loadByProductIds(List<String> productIds) {
        return repository.getProductsByIds(productIds);
    }

    public void updateStocks(Product p, Integer quantity) {
        saveAndNotify(
                p.withStocks(p.getStocks() - quantity),
                "product-stock-updated");
    }

    public Stream<Product> loadAllProducts() {
        return repository.getProducts(null);
    }

    public void createProduct(Product product) {
        saveAndNotify(product, "product-created");
    }

}
