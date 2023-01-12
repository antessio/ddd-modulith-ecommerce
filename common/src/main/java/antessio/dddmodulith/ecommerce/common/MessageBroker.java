package antessio.dddmodulith.ecommerce.common;

public interface MessageBroker {
    void sendMessage(Message message);
    void subscribe(Subscriber subscriber);


}
