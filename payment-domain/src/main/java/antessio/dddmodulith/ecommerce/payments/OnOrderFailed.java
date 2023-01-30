package antessio.dddmodulith.ecommerce.payments;

public interface OnOrderFailed {

    void reversePayment(PaymentReverseForOrder paymentConfirmationForOrder);
}
