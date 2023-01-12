package antessio.dddmodulith.ecommerce.api.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import antessio.dddmodulith.ecommerce.order.CreateOrderCommand;
import antessio.dddmodulith.ecommerce.order.OrderApplicationService;
import antessio.dddmodulith.ecommerce.order.OrderDTO;
import antessio.dddmodulith.ecommerce.order.PayOrderCommand;

@RestController
public class OrderController {

    private final OrderApplicationService orderApplicationService;

    public OrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @GetMapping("/orders/{id}")
    public OrderDTO getOrder(@PathVariable("id") String id) {
        return orderApplicationService.getOrder(id);
    }

    @PostMapping("/orders")
    public OrderDTO placeOrder(@RequestBody CreateOrderRequest request) {
        CreateOrderCommand createOrderCommand = CreateOrderCommand.of(
                request.getShippingAddress(),
                request.getPaymentId(),
                request.getUser()
        );
        request.getItems()
               .forEach(i -> createOrderCommand.addItem(i.getProductId(), i.getPriceAmountUnit(), i.getDescription(), i.getQuantity()));
        String orderId = orderApplicationService.createOrder(createOrderCommand);
        return orderApplicationService.getOrder(orderId);
    }

    @PutMapping("/orders/{id}")
    public OrderDTO payOrder(
            @PathVariable("id") String id,
            @RequestBody PayOrderRequest request) {
        orderApplicationService.payOrder(PayOrderCommand.of(id, request.getPaymentId()));
        return orderApplicationService.getOrder(id);
    }

}
