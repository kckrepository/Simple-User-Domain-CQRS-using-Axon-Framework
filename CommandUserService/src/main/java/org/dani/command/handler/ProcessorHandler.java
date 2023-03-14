package org.dani.command.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.dani.command.dto.MessageDto;
import org.dani.command.event.UserActivatedEvent;
import org.dani.command.event.UserCreatedEvent;
import org.dani.command.event.UserDeactivatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@ProcessingGroup("eventProcessor")
@Slf4j
public class ProcessorHandler {

    private final KafkaTemplate<String, MessageDto> kafkaTemplate;
    private final NewTopic userTopic;

    @EventHandler
    public void on(UserCreatedEvent event) {
        log.info("Event UserCreatedEvent - id : " + event.getId() + ", name : " + event.getFirstName());

        MessageDto msg = new MessageDto();
        msg.setId(event.getId());
        msg.setBirth(event.getBirth());
        msg.setFirstName(event.getFirstName());
        msg.setLastName(event.getLastName());
        msg.setCreated(event.getCreated());
        msg.setEvent("UserCreatedEvent");

        kafkaTemplate.send(userTopic.name(), msg);
    }

    @EventHandler
    public void on(UserActivatedEvent event) {
        log.info("Event UserActivatedEvent - id : " + event.getId() + ", updatedDate : " + event.getUpdated());

        MessageDto msg = new MessageDto();
        msg.setId(event.getId());
        msg.setCreated(event.getUpdated());
        msg.setEvent("UserActivatedEvent");

        kafkaTemplate.send(userTopic.name(), msg);
    }

    @EventHandler
    public void on(UserDeactivatedEvent event) {
        log.info("Event UserDeactivatedEvent - id : " + event.getId() + ", updatedDate : " + event.getUpdated());

        MessageDto msg = new MessageDto();
        msg.setId(event.getId());
        msg.setCreated(event.getUpdated());
        msg.setEvent("UserDeactivatedEvent");

        kafkaTemplate.send(userTopic.name(), msg);
    }

}
