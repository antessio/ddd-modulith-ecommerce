package antessio.dddmodulith.ecommerce.product;

import java.util.List;

public final class CreateProductCommand {
    private final Long priceAmountUnit;
    private final String description;
    private final Long stocks;
    private final List<String> images;

    private CreateProductCommand(Long priceAmountUnit, String description, Long stocks, List<String> images) {
        this.priceAmountUnit = priceAmountUnit;
        this.description = description;
        this.stocks = stocks;
        this.images = images;
    }

    public static CreateProductCommand of(Long priceAmountUnit, String description, Long stocks, List<String> images) {
        return new CreateProductCommand(priceAmountUnit, description, stocks, images);
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

    public List<String> getImages() {
        return this.images;
    }

    public CreateProductCommand withPriceAmountUnit(Long priceAmountUnit) {
        return of(priceAmountUnit, getDescription(), getStocks(), getImages());
    }

    public CreateProductCommand withDescription(String description) {
        return of(getPriceAmountUnit(), description, getStocks(), getImages());
    }

    public CreateProductCommand withStocks(Long stocks) {
        return of(getPriceAmountUnit(), getDescription(), stocks, getImages());
    }

    public CreateProductCommand withImages(List<String> images) {
        return of(getPriceAmountUnit(), getDescription(), getStocks(), images);
    }


}
