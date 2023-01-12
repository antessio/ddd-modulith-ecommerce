package antessio.dddmodulith.ecommerce.payments;

import java.util.UUID;

public class Payment {

    private  String id;
    private  String status;
    private  String accountId;
    private  Long amountUnit;
    private  String orderId;

    private Payment(String status, String accountId, Long amountUnit, String orderId) {
        this.id = UUID.randomUUID().toString();
        this.status = status;
        this.accountId = accountId;
        this.amountUnit = amountUnit;
        this.orderId = orderId;
    }
    private Payment(String id, String status, String accountId, Long amountUnit, String orderId) {
        this.id = id;
        this.status = status;
        this.accountId = accountId;
        this.amountUnit = amountUnit;
        this.orderId = orderId;
    }

    public Payment() {
        
    }

    static Payment of(String status, String accountId, Long amountUnit, String orderId) {
        return new Payment(status, accountId, amountUnit, orderId);
    }
    private static Payment of(String id, String status, String accountId, Long amountUnit, String orderId) {
        return new Payment(id, status, accountId, amountUnit, orderId);
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
        return of(getId(), status, getAccountId(), getAmountUnit(), getOrderId());
    }

    public Payment withAccountId(String accountId) {
        return of(getId(), getStatus(), accountId, getAmountUnit(), getOrderId());
    }

    public Payment withAmountUnit(Long amountUnit) {
        return of(getId(), getStatus(), getAccountId(), amountUnit, getOrderId());
    }

    public Payment withOrderId(String orderId) {
        return of(getId(), getStatus(), getAccountId(), getAmountUnit(), orderId);
    }

}
