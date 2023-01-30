package antessio.dddmodulith.ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import antessio.dddmodulith.ecommerce.common.ObjectMapperSerializationService;
import antessio.dddmodulith.ecommerce.common.SimpleMessageBroker;
import antessio.dddmodulith.ecommerce.database.order.OrderRepositoryImpl;
import antessio.dddmodulith.ecommerce.database.payment.PaymentRepositoryImpl;
import antessio.dddmodulith.ecommerce.database.shipping.ShippingRepositoryImpl;
import antessio.dddmodulith.ecommerce.notification.NotificationApplicationService;
import antessio.dddmodulith.ecommerce.order.OrderApplicationService;
import antessio.dddmodulith.ecommerce.payments.PaymentApplicationService;
import antessio.dddmodulith.ecommerce.product.ProductApplicationService;
import antessio.dddmodulith.ecommerce.database.product.ProductRepositoryImpl;
import antessio.dddmodulith.ecommerce.shipping.ShippingApplicationService;

@Configuration
public class ModuleConfiguration {

    @Bean
    public NotificationApplicationService notificationApplicationService() {
        return new NotificationApplicationService();
    }

    @Bean
    public ProductApplicationService productApplicationService(
            ObjectMapperSerializationService objectMapperSerializationService,
            SimpleMessageBroker simpleMessageBroker,
            ProductRepositoryImpl productRepository) {
        return new ProductApplicationService(objectMapperSerializationService, simpleMessageBroker,
                                             productRepository);
    }

    @Bean
    public OrderApplicationService orderApplicationService(
            ObjectMapperSerializationService objectMapperSerializationService,
            SimpleMessageBroker simpleMessageBroker,
            OrderRepositoryImpl orderRepository) {
        return new OrderApplicationService(
                objectMapperSerializationService,
                simpleMessageBroker,
                orderRepository);

    }

    @Bean
    public PaymentApplicationService paymentApplicationService(
            ObjectMapperSerializationService objectMapperSerializationService,
            SimpleMessageBroker simpleMessageBroker,
            PaymentRepositoryImpl paymentRepository) {
        return new PaymentApplicationService(objectMapperSerializationService, simpleMessageBroker,
                                             paymentRepository);
    }
    @Bean
    public ShippingApplicationService shippingApplicationService(
            ObjectMapperSerializationService objectMapperSerializationService,
            SimpleMessageBroker simpleMessageBroker,
            ShippingRepositoryImpl shippingRepository
    ){
        return new ShippingApplicationService(objectMapperSerializationService,simpleMessageBroker,
                                              shippingRepository);
    }

}
