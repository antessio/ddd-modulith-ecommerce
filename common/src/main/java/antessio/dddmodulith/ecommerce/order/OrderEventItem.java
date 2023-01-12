package antessio.dddmodulith.ecommerce.order;



public final class OrderEventItem {
    private  String productId;
    private  Long priceAmountUnit;
    private  String description;
    private  Integer quantity;

    public OrderEventItem() {
    }

    public OrderEventItem(String productId, Long priceAmountUnit, String description, Integer quantity) {
        this.productId = productId;
        this.priceAmountUnit = priceAmountUnit;
        this.description = description;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public Long getPriceAmountUnit() {
        return priceAmountUnit;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
    }

}
