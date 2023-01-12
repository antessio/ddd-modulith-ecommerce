package antessio.dddmodulith.ecommerce.api.order;

import java.util.List;

public class CreateOrderRequest {
    private String shippingAddress;
    private List<CreateOrderRequestItem> items;
    private  String paymentId;

    private String user;

    public CreateOrderRequest() {
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public List<CreateOrderRequestItem> getItems() {
        return items;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getUser() {
        return user;
    }

}
