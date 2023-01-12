package antessio.dddmodulith.ecommerce.api.payment;

public class PaymentFundLockCreateRequest {
    private String accountId;
    private Long amountUnit;

    public PaymentFundLockCreateRequest() {
    }


    public String getAccountId() {
        return accountId;
    }

    public Long getAmountUnit() {
        return amountUnit;
    }

}
