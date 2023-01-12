package antessio.dddmodulith.ecommerce.api.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import antessio.dddmodulith.ecommerce.payments.PaymentApplicationService;

@RestController
public class PaymentController {


    private final PaymentApplicationService paymentApplicationService;

    public PaymentController(PaymentApplicationService paymentApplicationService) {
        this.paymentApplicationService = paymentApplicationService;
    }

    @PostMapping("/payments")
    public ResponseEntity<String> createPayment(@RequestBody PaymentFundLockCreateRequest request){
        String paymentId = paymentApplicationService.createFundLock(request.getAccountId(), request.getAmountUnit());
        return ResponseEntity.ok(paymentId);
    }

}
