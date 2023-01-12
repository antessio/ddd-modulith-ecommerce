package antessio.dddmodulith.ecommerce.order;



final class Item {
    private final String productId;
    private final Long priceAmountUnit;
    private final String description;

    private Item(String productId, Long priceAmountUnit, String description) {
        this.productId = productId;
        this.priceAmountUnit = priceAmountUnit;
        this.description = description;
    }

    public static Item of(String productId, Long priceAmountUnit, String description) {
        return new Item(productId, priceAmountUnit, description);
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

    public Item withProductId(String productId) {
        return of(productId, getPriceAmountUnit(), getDescription());
    }

    public Item withPriceAmountUnit(Long priceAmountUnit) {
        return of(getProductId(), priceAmountUnit, getDescription());
    }

    public Item withDescription(String description) {
        return of(getProductId(), getPriceAmountUnit(), description);
    }

}
