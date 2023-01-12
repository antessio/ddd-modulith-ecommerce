package antessio.dddmodulith.ecommerce.payments;

import java.util.UUID;

final class Payment {

    private final String id;
    private final String status;
    private final String accountId;
    private final Long amountUnit;
    private final String orderId;

    private Payment(String status, String accountId, Long amountUnit, String orderId) {
        this.id = UUID.randomUUID().toString();
        this.status = status;
        this.accountId = accountId;
        this.amountUnit = amountUnit;
        this.orderId = orderId;
    }

    public static Payment of(String status, String accountId, Long amountUnit, String orderId) {
        return new Payment(status, accountId, amountUnit, orderId);
    }

    public String getId() {
        return this.id;
    }

    public String getStatus() {
        return this.status;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public Long getAmountUnit() {
        return this.amountUnit;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public Payment withStatus(String status) {
        return of(status, getAccountId(), getAmountUnit(), getOrderId());
    }

    public Payment withAccountId(String accountId) {
        return of(getStatus(), accountId, getAmountUnit(), getOrderId());
    }

    public Payment withAmountUnit(Long amountUnit) {
        return of(getStatus(), getAccountId(), amountUnit, getOrderId());
    }

    public Payment withOrderId(String orderId) {
        return of(getStatus(), getAccountId(), getAmountUnit(), orderId);
    }

}
