package antessio.dddmodulith.ecommerce.common;

import java.util.function.Consumer;

public interface MessageBroker {
    void sendMessage(Message message);
    void subscribe(Subscriber subscriber);


}
