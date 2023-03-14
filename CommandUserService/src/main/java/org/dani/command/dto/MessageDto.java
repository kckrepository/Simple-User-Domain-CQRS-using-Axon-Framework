package org.dani.command.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class MessageDto {

    private UUID id;

    private String firstName;

    private String lastName;

    private Date birth;

    private Date created;

    private String event;

}
