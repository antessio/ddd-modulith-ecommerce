package antessio.dddmodulith.ecommerce.api.order;

public class CreateOrderRequestItem {
    private  String productId;
    private  Long priceAmountUnit;
    private  String description;
    private  Integer quantity;

    public CreateOrderRequestItem() {
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
