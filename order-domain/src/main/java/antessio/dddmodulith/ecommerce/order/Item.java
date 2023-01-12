package antessio.dddmodulith.ecommerce.order;



public class Item {
    private String productId;
    private Long priceAmountUnit;
    private String description;
    private Integer quantity;

    private Item(String productId, Long priceAmountUnit, String description, Integer quantity) {
        this.productId = productId;
        this.priceAmountUnit = priceAmountUnit;
        this.description = description;
        this.quantity = quantity;
    }

    public Item() {

    }

    public static Item of(String productId, Long priceAmountUnit, String description, Integer quantity) {
        return new Item(productId, priceAmountUnit, description, quantity);
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

    public Item withProductId(String productId) {
        return of(productId, getPriceAmountUnit(), getDescription(), getQuantity());
    }

    public Item withPriceAmountUnit(Long priceAmountUnit) {
        return of(getProductId(), priceAmountUnit, getDescription(), getQuantity());
    }

    public Item withDescription(String description) {
        return of(getProductId(), getPriceAmountUnit(), description, getQuantity());
    }

    public Item withQuantity(Integer quantity) {
        return of(getProductId(), getPriceAmountUnit(), getDescription(), quantity);
    }

}
