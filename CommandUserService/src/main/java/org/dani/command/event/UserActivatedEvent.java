package org.dani.command.event;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserActivatedEvent {
    private UUID id;

    private Date updated;
}
