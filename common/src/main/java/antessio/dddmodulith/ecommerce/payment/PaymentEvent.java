package antessio.dddmodulith.ecommerce.payment;

import java.util.UUID;

public final class PaymentEvent {

    private final String id;
    private final String status;
    private final String accountId;
    private final Long amountUnit;
    private final String orderId;

    public PaymentEvent(String id, String status, String accountId, Long amountUnit, String orderId) {
        this.id = id;
        this.status = status;
        this.accountId = accountId;
        this.amountUnit = amountUnit;
        this.orderId = orderId;
    }

    public static PaymentEvent of(String id, String status, String accountId, Long amountUnit, String orderId) {
        return new PaymentEvent(id, status, accountId, amountUnit, orderId);
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

    public PaymentEvent withId(String id) {
        return of(id, getStatus(), getAccountId(), getAmountUnit(), getOrderId());
    }

    public PaymentEvent withStatus(String status) {
        return of(getId(), status, getAccountId(), getAmountUnit(), getOrderId());
    }

    public PaymentEvent withAccountId(String accountId) {
        return of(getId(), getStatus(), accountId, getAmountUnit(), getOrderId());
    }

    public PaymentEvent withAmountUnit(Long amountUnit) {
        return of(getId(), getStatus(), getAccountId(), amountUnit, getOrderId());
    }

    public PaymentEvent withOrderId(String orderId) {
        return of(getId(), getStatus(), getAccountId(), getAmountUnit(), orderId);
    }

}
