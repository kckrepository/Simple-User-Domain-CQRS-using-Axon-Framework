package org.dani.query.dto;

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
