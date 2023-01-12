package antessio.dddmodulith.ecommerce.common;

import java.util.function.Consumer;

public final class Subscriber {
    private final String event;
    private final Consumer<String> eventConsumer;

    private Subscriber(String event, Consumer<String> eventConsumer) {
        this.event = event;
        this.eventConsumer = eventConsumer;
    }

    public static Subscriber of(String event, Consumer<String> eventConsumer) {
        return new Subscriber(event, eventConsumer);
    }

    public String getEvent() {
        return this.event;
    }

    public Consumer<String> getEventConsumer() {
        return this.eventConsumer;
    }

    public Subscriber withEvent(String event) {
        return of(event, getEventConsumer());
    }

    public Subscriber withEventConsumer(Consumer<String> eventConsumer) {
        return of(getEvent(), eventConsumer);
    }


}
