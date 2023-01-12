package antessio.dddmodulith.ecommerce.common;

import java.util.List;

public class SimpleMessageBroker implements MessageBroker {

    private List<Message> messageList;
    private List<Subscriber> subscriberList;

    @Override
    public void sendMessage(Message message) {
        messageList.add(message);
        notifySubscribers(message);
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        subscriberList.add(subscriber);
        replayEvents(subscriber);
    }

    private void replayEvents(Subscriber subscriber) {
        messageList.stream()
                   .filter(m -> m.getEvent().equals(subscriber.getEvent()))
                   .forEach(m -> subscriber.getEventConsumer().accept(m.getContent()));
    }

    private void notifySubscribers(Message message) {
        subscriberList
                .stream()
                .filter(s -> s.getEvent().equals(message.getEvent()))
                .forEach(s -> s.getEventConsumer().accept(message.getEvent()));
    }


}
