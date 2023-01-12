package antessio.dddmodulith.ecommerce.order;

public final class PayOrderCommand {
    private final String orderId;
    private final String paymentId;

    private PayOrderCommand(String orderId, String paymentId) {
        this.orderId = orderId;
        this.paymentId = paymentId;
    }

    public static PayOrderCommand of(String orderId, String paymentId) {
        return new PayOrderCommand(orderId, paymentId);
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getPaymentId() {
        return this.paymentId;
    }

    public PayOrderCommand withOrderId(String orderId) {
        return of(orderId, getPaymentId());
    }

    public PayOrderCommand withPaymentId(String paymentId) {
        return of(getOrderId(), paymentId);
    }


}
