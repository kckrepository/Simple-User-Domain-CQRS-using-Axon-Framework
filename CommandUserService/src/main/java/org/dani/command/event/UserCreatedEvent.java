package org.dani.command.event;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreatedEvent {
    private UUID id;

    private String firstName;

    private String lastName;

    private Date birth;

    private Date created;

    private Date updated;

}
