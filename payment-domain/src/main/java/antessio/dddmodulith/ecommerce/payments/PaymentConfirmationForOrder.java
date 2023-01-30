package antessio.dddmodulith.ecommerce.payments;

public final class PaymentConfirmationForOrder {
    private final String orderId;
    private final String paymentId;

    private PaymentConfirmationForOrder(String orderId, String paymentId) {
        this.orderId = orderId;
        this.paymentId = paymentId;
    }

    public static PaymentConfirmationForOrder of(String orderId, String paymentId) {
        return new PaymentConfirmationForOrder(orderId, paymentId);
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getPaymentId() {
        return this.paymentId;
    }

    public PaymentConfirmationForOrder withOrderId(String orderId) {
        return of(orderId, getPaymentId());
    }

    public PaymentConfirmationForOrder withPaymentId(String paymentId) {
        return of(getOrderId(), paymentId);
    }

}
