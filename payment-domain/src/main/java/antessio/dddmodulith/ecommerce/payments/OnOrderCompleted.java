package antessio.dddmodulith.ecommerce.payments;

public interface OnOrderCompleted {

    void confirmPayment(PaymentConfirmationForOrder paymentConfirmationForOrder);
}
