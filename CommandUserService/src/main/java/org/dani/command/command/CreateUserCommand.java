package org.dani.command.command;

import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;
import java.util.UUID;

@Getter
@Builder
public class CreateUserCommand {

    @TargetAggregateIdentifier
    private UUID id;

    private String firstName;

    private String lastName;

    private Date birth;

    private Date created;

}
