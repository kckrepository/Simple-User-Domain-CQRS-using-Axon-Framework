package org.dani.command.aggregate;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.dani.command.command.ActiveUserCommand;
import org.dani.command.command.CreateUserCommand;
import org.dani.command.command.DeactivateUserCommand;
import org.dani.command.event.UserActivatedEvent;
import org.dani.command.event.UserCreatedEvent;
import org.dani.command.event.UserDeactivatedEvent;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

public class UserAggregateTest {

    private FixtureConfiguration fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture(UserAggregate.class);
    }

    @Test
    public void createUser() {
        var id = UUID.randomUUID();
        var theDate = new Date();

        UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder()
                .id(id)
                .birth(theDate)
                .created(theDate)
                .firstName("myname")
                .lastName("endName")
                .build();

        CreateUserCommand createUserCommand = CreateUserCommand.builder()
                .id(id)
                .birth(theDate)
                .created(theDate)
                .firstName("myname")
                .lastName("endName")
                .build();

        fixture.given().when(createUserCommand)
                .expectSuccessfulHandlerExecution()
                .expectEvents(userCreatedEvent);
    }

    @Test
    public void activateUser() {
        var id = UUID.randomUUID();
        var theDate = new Date();

        UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder()
                .id(id)
                .birth(theDate)
                .created(theDate)
                .firstName("myname")
                .lastName("endName")
                .build();

        ActiveUserCommand activeUserCommand = ActiveUserCommand.builder()
                .id(id)
                .updated(theDate)
                .build();

        UserActivatedEvent userActivatedEvent = UserActivatedEvent.builder()
                .id(id)
                .updated(theDate)
                .build();

        fixture.given(userCreatedEvent)
                .when(activeUserCommand)
                .expectSuccessfulHandlerExecution()
                .expectEvents(userActivatedEvent);
    }

    @Test
    public void deactivateUser() {
        var id = UUID.randomUUID();
        var theDate = new Date();

        UserActivatedEvent userActivatedEvent = UserActivatedEvent.builder()
                .id(id)
                .updated(theDate)
                .build();

        DeactivateUserCommand deactivateUserCommand = DeactivateUserCommand.builder()
                .id(id)
                .updated(theDate)
                .build();

        UserDeactivatedEvent userDeactivatedEvent = UserDeactivatedEvent.builder()
                .id(id)
                .updated(theDate)
                .build();

        fixture.given(userActivatedEvent)
                .when(deactivateUserCommand)
                .expectSuccessfulHandlerExecution()
                .expectEvents(userDeactivatedEvent);
    }

}
