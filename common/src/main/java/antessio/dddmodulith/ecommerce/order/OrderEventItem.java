package antessio.dddmodulith.ecommerce.order;



public final class OrderEventItem {
    private final String productId;
    private final Long priceAmountUnit;
    private final String description;

    private OrderEventItem(String productId, Long priceAmountUnit, String description) {
        this.productId = productId;
        this.priceAmountUnit = priceAmountUnit;
        this.description = description;
    }

    public static OrderEventItem of(String productId, Long priceAmountUnit, String description) {
        return new OrderEventItem(productId, priceAmountUnit, description);
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

    public OrderEventItem withProductId(String productId) {
        return of(productId, getPriceAmountUnit(), getDescription());
    }

    public OrderEventItem withPriceAmountUnit(Long priceAmountUnit) {
        return of(getProductId(), priceAmountUnit, getDescription());
    }

    public OrderEventItem withDescription(String description) {
        return of(getProductId(), getPriceAmountUnit(), description);
    }

}
