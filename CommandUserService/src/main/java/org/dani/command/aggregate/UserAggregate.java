package org.dani.command.aggregate;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.dani.command.command.ActiveUserCommand;
import org.dani.command.command.CreateUserCommand;
import org.dani.command.command.DeactivateUserCommand;
import org.dani.command.event.UserActivatedEvent;
import org.dani.command.event.UserCreatedEvent;
import org.dani.command.event.UserDeactivatedEvent;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
public class UserAggregate {

    @AggregateIdentifier
    private UUID id;

    protected UserAggregate() {}

    @CommandHandler
    public UserAggregate(CreateUserCommand command) {
        UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder().id(command.getId())
                        .firstName(command.getFirstName())
                                .lastName(command.getLastName())
                                        .birth(command.getBirth())
                                                .created(command.getCreated())
                                                        .build();
        apply(userCreatedEvent);
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent event) {
        this.id = event.getId();
    }

    @CommandHandler
    public void handle(ActiveUserCommand command) {
        UserActivatedEvent userActivatedEvent = UserActivatedEvent.builder()
                .id(command.getId())
                .updated(command.getUpdated())
                .build();
        apply(userActivatedEvent);
    }

    @EventSourcingHandler
    public void on(UserActivatedEvent event) {
        this.id = event.getId();
    }

    @CommandHandler
    public void handle(DeactivateUserCommand command) {
        UserDeactivatedEvent userDeactivatedEvent = UserDeactivatedEvent.builder()
                .id(command.getId())
                .updated(command.getUpdated())
                .build();
        apply(userDeactivatedEvent);
    }

    @EventSourcingHandler
    public void on(UserDeactivatedEvent event) {
        this.id = event.getId();
    }

    @ExceptionHandler
    public void handleAll(Exception exception) {
        log.error("Exception : " + exception);
    }

}
