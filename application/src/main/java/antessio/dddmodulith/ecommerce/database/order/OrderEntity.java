package antessio.dddmodulith.ecommerce.database.order;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import antessio.dddmodulith.ecommerce.order.Item;
import antessio.dddmodulith.ecommerce.order.Order;

@Entity
@Table(name = "ecommerce_order")
public class OrderEntity extends Order {

    @Id
    private  String id;
    private  String shippingAddress;
    @OneToMany(
            cascade = CascadeType.ALL, mappedBy = "orderId"
    )
    private Set<ItemEntity> items;
    private  String paymentId;
    private  String shippingId;
    private  String shippingStatus;
    private  String username;

    public OrderEntity() {
        super();
    }

    public OrderEntity(String id, String shippingAddress, Set<ItemEntity> items, String paymentId, String shippingId, String shippingStatus, String username) {
        this.id = id;
        this.shippingAddress = shippingAddress;
        this.items = items;
        this.paymentId = paymentId;
        this.shippingId = shippingId;
        this.shippingStatus = shippingStatus;
        this.username = username;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getShippingAddress() {
        return shippingAddress;
    }

    public Set<ItemEntity> getItemsEntity() {
        return items;
    }

    @Override
    public List<Item> getItems() {
        return items
                .stream()
                .map(Item.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public String getPaymentId() {
        return paymentId;
    }

    @Override
    public String getShippingId() {
        return shippingId;
    }

    @Override
    public String getShippingStatus() {
        return shippingStatus;
    }

    @Override
    public String getUsername() {
        return username;
    }

}
