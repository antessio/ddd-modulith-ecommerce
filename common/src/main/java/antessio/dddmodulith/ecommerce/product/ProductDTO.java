package antessio.dddmodulith.ecommerce.product;

import java.util.List;

public final class ProductDTO {
    private final String productId;
    private final Long priceAmountUnit;
    private final String description;
    private final Long stocks;
    private final Integer rate;
    private final List<String> images;

    private ProductDTO(String productId, Long priceAmountUnit, String description, Long stocks, Integer rate, List<String> images) {
        this.productId = productId;
        this.priceAmountUnit = priceAmountUnit;
        this.description = description;
        this.stocks = stocks;
        this.rate = rate;
        this.images = images;
    }

    public static ProductDTO of(String productId, Long priceAmountUnit, String description, Long stocks, Integer rate, List<String> images) {
        return new ProductDTO(productId, priceAmountUnit, description, stocks, rate, images);
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

    public ProductDTO withProductId(String productId) {
        return of(productId, getPriceAmountUnit(), getDescription(), getStocks(), getRate(), getImages());
    }

    public ProductDTO withPriceAmountUnit(Long priceAmountUnit) {
        return of(getProductId(), priceAmountUnit, getDescription(), getStocks(), getRate(), getImages());
    }

    public ProductDTO withDescription(String description) {
        return of(getProductId(), getPriceAmountUnit(), description, getStocks(), getRate(), getImages());
    }

    public ProductDTO withStocks(Long stocks) {
        return of(getProductId(), getPriceAmountUnit(), getDescription(), stocks, getRate(), getImages());
    }

    public ProductDTO withRate(Integer rate) {
        return of(getProductId(), getPriceAmountUnit(), getDescription(), getStocks(), rate, getImages());
    }

    public ProductDTO withImages(List<String> images) {
        return of(getProductId(), getPriceAmountUnit(), getDescription(), getStocks(), getRate(), images);
    }

}
