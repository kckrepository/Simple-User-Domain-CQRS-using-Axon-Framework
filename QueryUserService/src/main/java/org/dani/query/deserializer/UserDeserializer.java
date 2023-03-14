package org.dani.query.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.dani.query.dto.MessageDto;

import java.util.Map;

@Slf4j
public class UserDeserializer implements org.apache.kafka.common.serialization.Deserializer<MessageDto> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public MessageDto deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(new String(data, "UTF-8"), MessageDto.class);
        } catch (Exception e) {
            log.error("UserDeserializer error : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
    }
}
