package antessio.dddmodulith.ecommerce.common;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ObjectMapperSerializationService implements SerializationService{

    private final ObjectMapper objectMapper;

    public ObjectMapperSerializationService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> String serialize(T obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T deserialize(String raw, Class<T> cls) {
        try {
            return objectMapper.readValue(raw,cls);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
