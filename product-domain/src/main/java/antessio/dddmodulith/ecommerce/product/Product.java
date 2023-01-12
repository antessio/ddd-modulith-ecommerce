package antessio.dddmodulith.ecommerce.product;

import java.util.List;
import java.util.UUID;

public class Product {
    private String productId;
    private Long priceAmountUnit;
    private String description;
    private Long stocks;
    private Integer rate;
    private List<String> images;

    public Product(){

    }

    private Product(Long priceAmountUnit, String description, Long stocks, Integer rate, List<String> images) {
        this.productId = UUID.randomUUID().toString();
        this.priceAmountUnit = priceAmountUnit;
        this.description = description;
        this.stocks = stocks;
        this.rate = rate;
        this.images = images;
    }

    private Product(String productId, Long priceAmountUnit, String description, Long stocks, Integer rate, List<String> images) {
        this.productId = productId;
        this.priceAmountUnit = priceAmountUnit;
        this.description = description;
        this.stocks = stocks;
        this.rate = rate;
        this.images = images;
    }
    static Product of(Long priceAmountUnit, String description, Long stocks, Integer rate, List<String> images) {
        return new Product(priceAmountUnit, description, stocks, rate, images);
    }
    private static Product of(String productId, Long priceAmountUnit, String description, Long stocks, Integer rate, List<String> images) {
        return new Product(productId, priceAmountUnit, description, stocks, rate, images);
    }

    public String getProductId() {
        return this.productId;
    }

    public Long getPriceAmountUnit() {
        return this.priceAmountUnit;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getStocks() {
        return this.stocks;
    }

    public Integer getRate() {
        return this.rate;
    }

    public List<String> getImages() {
        return this.images;
    }

    public Product withPriceAmountUnit(Long priceAmountUnit) {
        return of(getProductId(), priceAmountUnit, getDescription(), getStocks(), getRate(), getImages());
    }

    public Product withDescription(String description) {
        return of(getProductId(), getPriceAmountUnit(), description, getStocks(), getRate(), getImages());
    }

    public Product withStocks(Long stocks) {
        return of(getProductId(), getPriceAmountUnit(), getDescription(), stocks, getRate(), getImages());
    }

    public Product withRate(Integer rate) {
        return of(getProductId(), getPriceAmountUnit(), getDescription(), getStocks(), rate, getImages());
    }

    public Product withImages(List<String> images) {
        return of(getProductId(), getPriceAmountUnit(), getDescription(), getStocks(), getRate(), images);
    }

}
