package org.dani.command.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.dani.command.dto.MessageDto;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class UserSerializer implements org.apache.kafka.common.serialization.Serializer<MessageDto> {
    private ObjectMapper objectMapper = new ObjectMapper();

    public void configure(Map map, boolean b) {}

    public byte[] serialize(String s, MessageDto o) {
        try {
            return objectMapper.writeValueAsBytes(o);
        } catch (IOException e) {
            log.error("UserSerializer error : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void close() {}

}