package antessio.dddmodulith.ecommerce.database.product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import antessio.dddmodulith.ecommerce.product.Product;
import antessio.dddmodulith.ecommerce.product.ProductRepository;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    public ProductRepositoryImpl(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public Stream<Product> getProducts(String startingFrom) {
        return jpaProductRepository.findAll()
                                   .stream()
                                   .map(Product.class::cast);
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return jpaProductRepository.findById(id)
                                   .map(Product.class::cast);
    }

    @Override
    public List<Product> getProductsByIds(List<String> ids) {
        return jpaProductRepository.findAllById(ids)
                                   .stream()
                                   .map(Product.class::cast)
                                   .collect(Collectors.toList());
    }

    @Override
    public void saveProduct(Product product) {
        jpaProductRepository.save(new ProductEntity(
                product.getProductId(),
                product.getPriceAmountUnit(),
                product.getDescription(),
                product.getStocks(),
                product.getRate(),
                product.getImages()
        ));
    }

}
