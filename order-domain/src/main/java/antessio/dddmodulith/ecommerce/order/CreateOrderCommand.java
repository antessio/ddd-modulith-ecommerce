package antessio.dddmodulith.ecommerce.order;

import java.util.List;


public final class CreateOrderCommand {
    private final String shippingAddress;
    private final List<Item> items;

    private final String paymentId;

    private CreateOrderCommand(String shippingAddress, List<Item> items, String paymentId) {
        this.shippingAddress = shippingAddress;
        this.items = items;
        this.paymentId = paymentId;
    }

    public static CreateOrderCommand of(String shippingAddress, List<Item> items, String paymentId) {
        return new CreateOrderCommand(shippingAddress, items, paymentId);
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public String getPaymentId() {
        return this.paymentId;
    }

    public CreateOrderCommand withShippingAddress(String shippingAddress) {
        return of(shippingAddress, getItems(), getPaymentId());
    }

    public CreateOrderCommand withItems(List<Item> items) {
        return of(getShippingAddress(), items, getPaymentId());
    }

    public CreateOrderCommand withPaymentId(String paymentId) {
        return of(getShippingAddress(), getItems(), paymentId);
    }

}
