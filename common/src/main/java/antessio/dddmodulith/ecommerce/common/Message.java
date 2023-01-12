package antessio.dddmodulith.ecommerce.common;

public final class Message {
    private final String event;
    private final String content;


    private Message(String event, String content) {
        this.event = event;
        this.content = content;
    }

    public static Message of(String event, String content) {
        return new Message(event, content);
    }

    public String getEvent() {
        return this.event;
    }

    public String getContent() {
        return this.content;
    }

    public Message withEvent(String event) {
        return of(event, getContent());
    }

    public Message withContent(String content) {
        return of(getEvent(), content);
    }

}
