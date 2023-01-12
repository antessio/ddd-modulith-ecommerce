package antessio.dddmodulith.ecommerce.database.payment;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import antessio.dddmodulith.ecommerce.payments.Payment;

@Entity
@Table(name = "payment")
public class PaymentEntity extends Payment {

    @Id
    private  String id;
    private  String status;
    private  String accountId;
    private  Long amountUnit;
    private  String orderId;

    public PaymentEntity() {
        super();
    }

    public PaymentEntity(String id, String status, String accountId, Long amountUnit, String orderId) {
        this.id = id;
        this.status = status;
        this.accountId = accountId;
        this.amountUnit = amountUnit;
        this.orderId = orderId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getAccountId() {
        return accountId;
    }

    @Override
    public Long getAmountUnit() {
        return amountUnit;
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

}
