package antessio.dddmodulith.ecommerce.database.shipping;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import antessio.dddmodulith.ecommerce.shipping.Shipping;

@Entity
@Table(name = "shipping")
public class ShippingEntity extends Shipping {

    @Id
    private  String shippingId;
    private  String orderId;

    private  String courier;

    private  String status;

    private  String shippingAddress;

    public ShippingEntity(String shippingId, String orderId, String courier, String status, String shippingAddress) {
        this.shippingId = shippingId;
        this.orderId = orderId;
        this.courier = courier;
        this.status = status;
        this.shippingAddress = shippingAddress;
    }

    public ShippingEntity() {

    }

    @Override
    public String getShippingId() {
        return shippingId;
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public String getCourier() {
        return courier;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getShippingAddress() {
        return shippingAddress;
    }

}
