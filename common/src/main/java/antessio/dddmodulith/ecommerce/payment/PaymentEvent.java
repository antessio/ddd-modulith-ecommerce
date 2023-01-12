package antessio.dddmodulith.ecommerce.payment;



public  class PaymentEvent {

    private String id;
    private String status;
    private String accountId;
    private Long amountUnit;
    private String orderId;

    public PaymentEvent() {
    }

    public PaymentEvent(String id, String status, String accountId, Long amountUnit, String orderId) {
        this.id = id;
        this.status = status;
        this.accountId = accountId;
        this.amountUnit = amountUnit;
        this.orderId = orderId;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getAccountId() {
        return accountId;
    }

    public Long getAmountUnit() {
        return amountUnit;
    }

    public String getOrderId() {
        return orderId;
    }

}