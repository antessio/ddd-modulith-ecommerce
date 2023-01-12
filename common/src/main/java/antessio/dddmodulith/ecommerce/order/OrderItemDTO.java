package antessio.dddmodulith.ecommerce.order;



public final class OrderItemDTO {
    private final String productId;
    private final Long priceAmountUnit;
    private final String description;
    private final Integer quantity;

    private OrderItemDTO(String productId, Long priceAmountUnit, String description, Integer quantity) {
        this.productId = productId;
        this.priceAmountUnit = priceAmountUnit;
        this.description = description;
        this.quantity = quantity;
    }

    public static OrderItemDTO of(String productId, Long priceAmountUnit, String description, Integer quantity) {
        return new OrderItemDTO(productId, priceAmountUnit, description, quantity);
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

    public OrderItemDTO withProductId(String productId) {
        return of(productId, getPriceAmountUnit(), getDescription(), getQuantity());
    }

    public OrderItemDTO withPriceAmountUnit(Long priceAmountUnit) {
        return of(getProductId(), priceAmountUnit, getDescription(), getQuantity());
    }

    public OrderItemDTO withDescription(String description) {
        return of(getProductId(), getPriceAmountUnit(), description, getQuantity());
    }

    public OrderItemDTO withQuantity(Integer quantity) {
        return of(getProductId(), getPriceAmountUnit(), getDescription(), quantity);
    }

}
