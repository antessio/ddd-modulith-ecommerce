package antessio.dddmodulith.ecommerce.payments;

import java.util.Optional;

public interface PaymentRepository {

    Optional<Payment> getPaymentById(String id);

    Optional<Payment> getPaymentByOrderId(String orderId);

    String save(Payment payment);

}
