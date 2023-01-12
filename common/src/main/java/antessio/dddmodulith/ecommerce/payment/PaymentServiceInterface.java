package antessio.dddmodulith.ecommerce.payment;

public interface PaymentServiceInterface {

    String createFundLock(String accountId, Long amountUnit);

}
