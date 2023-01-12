package antessio.dddmodulith.ecommerce.common;

public abstract class DomainService<T> {

    private final SerializationService serializationService;
    private final MessageBroker messageBroker;


    public DomainService(SerializationService serializationService, MessageBroker messageBroker) {
        this.serializationService = serializationService;
        this.messageBroker = messageBroker;
    }

    protected T saveAndNotify(T obj,
                              String eventType) {
        save(obj);
        Object event = convertToEvent(obj);
        String content = serializationService.serialize(event);
        messageBroker.sendMessage(Message.of(eventType, content));
        return obj;
    }

    protected abstract T save(T obj);

    protected abstract Object convertToEvent(T obj);

}
