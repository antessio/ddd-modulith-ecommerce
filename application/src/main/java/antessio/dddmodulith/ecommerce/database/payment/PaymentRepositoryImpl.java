package antessio.dddmodulith.ecommerce.database.payment;

import java.util.Optional;

import org.springframework.stereotype.Component;

import antessio.dddmodulith.ecommerce.payments.Payment;
import antessio.dddmodulith.ecommerce.payments.PaymentRepository;

@Component
public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentJpaRepository jpaRepository;

    public PaymentRepositoryImpl(PaymentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Payment> getPaymentById(String id) {
        return jpaRepository.findById(id)
                .map(Payment.class::cast);
    }

    @Override
    public Optional<Payment> getPaymentByOrderId(String orderId) {
        return jpaRepository.findByOrderId(orderId)
                .map(Payment.class::cast);
    }

    @Override
    public String save(Payment payment) {
        jpaRepository.save(new PaymentEntity(
                payment.getId(),
                payment.getStatus(),
                payment.getAccountId(),
                payment.getAmountUnit(),
                payment.getOrderId()
        ));
        return payment.getId();
    }

}
