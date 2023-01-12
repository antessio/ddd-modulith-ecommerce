package antessio.dddmodulith.ecommerce.api.shipping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import antessio.dddmodulith.ecommerce.shipping.ShippingApplicationService;
import antessio.dddmodulith.ecommerce.shipping.ShippingInfoDTO;

@RestController
public class ShippingController {
    private final ShippingApplicationService shippingApplicationService;

    public ShippingController(ShippingApplicationService shippingApplicationService) {
        this.shippingApplicationService = shippingApplicationService;
    }

    @GetMapping("/shippings/{id}")
    public ShippingInfoDTO getShipping(@PathVariable("id")String id){
        return this.shippingApplicationService.getShippingInfo(id);
    }

    @PutMapping("/shippings/{id}/start_delivery")
    public ShippingInfoDTO startDelivery(@PathVariable("id")String id){
        this.shippingApplicationService.startDelivery(id);
        return this.shippingApplicationService.getShippingInfo(id);
    }

    @PutMapping("/shippings/{id}/complete_delivery")
    public ShippingInfoDTO completeDelivery(@PathVariable("id")String id){
        this.shippingApplicationService.completeDelivery(id);
        return this.shippingApplicationService.getShippingInfo(id);
    }

}
