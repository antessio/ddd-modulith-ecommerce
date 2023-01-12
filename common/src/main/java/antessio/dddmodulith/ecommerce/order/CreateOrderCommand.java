package antessio.dddmodulith.ecommerce.order;

import java.util.ArrayList;
import java.util.List;


public final class CreateOrderCommand {

    private final String shippingAddress;
    private final List<CreateOrderItem> items;
    private final String paymentId;

    private final String user;

    private CreateOrderCommand(String shippingAddress, List<CreateOrderItem> items, String paymentId, String user) {
        this.shippingAddress = shippingAddress;
        this.items = items;
        this.paymentId = paymentId;
        this.user = user;
    }

    public static CreateOrderCommand of(String shippingAddress, String paymentId, String user) {
        return new CreateOrderCommand(shippingAddress, new ArrayList<>(), paymentId, user);
    }

    private static CreateOrderCommand of(String shippingAddress, List<CreateOrderItem> items, String paymentId, String user) {
        return new CreateOrderCommand(shippingAddress, items, paymentId, user);
    }

    public String getUser() {
        return user;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public List<CreateOrderItem> getItems() {
        return this.items;
    }

    public String getPaymentId() {
        return this.paymentId;
    }

    public CreateOrderCommand withShippingAddress(String shippingAddress) {
        return of(shippingAddress, getItems(), getPaymentId(),getUser());
    }

    public CreateOrderCommand withItems(List<CreateOrderItem> items) {
        return of(getShippingAddress(), items, getPaymentId(), getUser());
    }

    public void addItem(
            String productId,
            Long priceAmountUnit,
            String description,
            Integer quantity) {
        this.items.add(CreateOrderItem.of(productId, priceAmountUnit, description, quantity));

    }

    public CreateOrderCommand withPaymentId(String paymentId) {
        return of(getShippingAddress(), getItems(), paymentId, getUser());
    }

}
