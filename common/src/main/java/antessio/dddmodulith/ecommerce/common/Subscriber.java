package antessio.dddmodulith.ecommerce.common;

import java.util.function.Consumer;

public final class Subscriber {

    private final String id;
    private final String event;
    private final Consumer<String> eventConsumer;

    private Subscriber(String id, String event, Consumer<String> eventConsumer) {
        this.id = id;
        this.event = event;
        this.eventConsumer = eventConsumer;
    }

    public static Subscriber of(String id, String event, Consumer<String> eventConsumer) {
        return new Subscriber(id, event, eventConsumer);
    }

    public String getId() {
        return this.id;
    }

    public String getEvent() {
        return this.event;
    }

    public Consumer<String> getEventConsumer() {
        return this.eventConsumer;
    }

    public Subscriber withId(String id) {
        return of(id, getEvent(), getEventConsumer());
    }

    public Subscriber withEvent(String event) {
        return of(getId(), event, getEventConsumer());
    }

    public Subscriber withEventConsumer(Consumer<String> eventConsumer) {
        return of(getId(), getEvent(), eventConsumer);
    }

}
