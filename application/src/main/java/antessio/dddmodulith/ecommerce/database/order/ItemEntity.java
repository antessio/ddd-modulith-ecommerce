package antessio.dddmodulith.ecommerce.database.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import antessio.dddmodulith.ecommerce.order.Item;

@Entity
@Table(name = "order_item")
public class ItemEntity extends Item {
    @Id
    private  String productId;
    private  Long priceAmountUnit;
    private  String description;
    private  Integer quantity;

    private String orderId;

    public ItemEntity() {
        super();
    }

    public ItemEntity(String productId, Long priceAmountUnit, String description, Integer quantity, String orderId) {
        this.productId = productId;
        this.priceAmountUnit = priceAmountUnit;
        this.description = description;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    @Override
    public String getProductId() {
        return productId;
    }

    @Override
    public Long getPriceAmountUnit() {
        return priceAmountUnit;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    public String getOrderId() {
        return orderId;
    }

}
