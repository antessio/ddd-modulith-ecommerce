package antessio.dddmodulith.ecommerce.payments;

public final class PaymentReverseForOrder {
    private final String orderId;
    private final String paymentId;

    private PaymentReverseForOrder(String orderId, String paymentId) {
        this.orderId = orderId;
        this.paymentId = paymentId;
    }

    public static PaymentReverseForOrder of(String orderId, String paymentId) {
        return new PaymentReverseForOrder(orderId, paymentId);
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getPaymentId() {
        return this.paymentId;
    }

    public PaymentReverseForOrder withOrderId(String orderId) {
        return of(orderId, getPaymentId());
    }

    public PaymentReverseForOrder withPaymentId(String paymentId) {
        return of(getOrderId(), paymentId);
    }

}
