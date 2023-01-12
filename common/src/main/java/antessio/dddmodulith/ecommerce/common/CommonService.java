package antessio.dddmodulith.ecommerce.common;

import antessio.dddmodulith.ecommerce.order.OrderEvent;

public abstract class CommonService <T> {

    private final SerializationService serializationService;
    private final MessageBroker messageBroker;


    public CommonService(SerializationService serializationService, MessageBroker messageBroker) {
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
