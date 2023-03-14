package org.dani.command.command;

import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;
import java.util.UUID;

@Getter
@Builder
public class DeactivateUserCommand {
    @TargetAggregateIdentifier
    private UUID id;

    private Date updated;
}
