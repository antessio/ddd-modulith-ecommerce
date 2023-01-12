package antessio.dddmodulith.ecommerce.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleMessageBroker implements MessageBroker {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleMessageBroker.class);

    private List<Message> messageList = new ArrayList<>();
    private List<Subscriber> subscriberList = new ArrayList<>();


    public SimpleMessageBroker() {

    }

    @Override
    public void sendMessage(Message message) {
        LOG.info("message to send {} -  {} ", message.getEvent(), message.getContent());
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
                   .map(m -> (Runnable) () -> subscriber.getEventConsumer().accept(m.getContent()))
                   .forEach(Runnable::run);
    }

    private void notifySubscribers(Message message) {

        subscriberList
                .stream()
                .filter(s -> s.getEvent().equals(message.getEvent()))
                .peek(s -> LOG.info("notifying {}", s.getId()))
                .map(s -> (Runnable) () -> s.getEventConsumer().accept(message.getContent()))
                .forEach(Runnable::run);
    }


}
