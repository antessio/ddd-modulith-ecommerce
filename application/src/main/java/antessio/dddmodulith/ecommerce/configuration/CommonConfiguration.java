package antessio.dddmodulith.ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import antessio.dddmodulith.ecommerce.common.SimpleMessageBroker;

@Configuration
public class CommonConfiguration {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
    @Bean
    public SimpleMessageBroker simpleMessageBroker(){
        return new SimpleMessageBroker();
    }

}
