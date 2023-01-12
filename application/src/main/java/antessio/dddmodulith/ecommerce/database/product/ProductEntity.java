package antessio.dddmodulith.ecommerce.database.product;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import antessio.dddmodulith.ecommerce.product.Product;

@Entity
@Table(name = "product")
public class ProductEntity extends Product{

    @Id
    private String productId;
    private Long priceAmountUnit;
    private String description;
    private Long stocks;
    private Integer rate;
    @ElementCollection
    private List<String> images;

    public ProductEntity() {
        super();
    }

    public ProductEntity(String productId, Long priceAmountUnit, String description, Long stocks, Integer rate, List<String> images) {
        this.productId = productId;
        this.priceAmountUnit = priceAmountUnit;
        this.description = description;
        this.stocks = stocks;
        this.rate = rate;
        this.images = images;
    }

    @Override
    public String getProductId() {
        return productId;
    }

    @Override
    public Long getPriceAmountUnit() {
        return priceAmountUnit;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Long getStocks() {
        return stocks;
    }

    @Override
    public Integer getRate() {
        return rate;
    }

    @Override
    public List<String> getImages() {
        return images;
    }

}
