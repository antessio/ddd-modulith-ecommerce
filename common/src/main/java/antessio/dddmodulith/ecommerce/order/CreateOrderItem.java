package antessio.dddmodulith.ecommerce.order;



final class CreateOrderItem {
    private final String productId;
    private final Long priceAmountUnit;
    private final String description;
    private final Integer quantity;

    private CreateOrderItem(String productId, Long priceAmountUnit, String description, Integer quantity) {
        this.productId = productId;
        this.priceAmountUnit = priceAmountUnit;
        this.description = description;
        this.quantity = quantity;
    }

    public static CreateOrderItem of(String productId, Long priceAmountUnit, String description, Integer quantity) {
        return new CreateOrderItem(productId, priceAmountUnit, description, quantity);
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

    public Integer getQuantity() {
        return this.quantity;
    }

    public CreateOrderItem withProductId(String productId) {
        return of(productId, getPriceAmountUnit(), getDescription(), getQuantity());
    }

    public CreateOrderItem withPriceAmountUnit(Long priceAmountUnit) {
        return of(getProductId(), priceAmountUnit, getDescription(), getQuantity());
    }

    public CreateOrderItem withDescription(String description) {
        return of(getProductId(), getPriceAmountUnit(), description, getQuantity());
    }

    public CreateOrderItem withQuantity(Integer quantity) {
        return of(getProductId(), getPriceAmountUnit(), getDescription(), quantity);
    }

}
