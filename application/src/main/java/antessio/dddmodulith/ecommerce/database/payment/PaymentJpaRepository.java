package antessio.dddmodulith.ecommerce.database.payment;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentEntity,String> {

    Optional<PaymentEntity> findByOrderId(String orderId);

}
