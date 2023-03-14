package org.dani.query.entity;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;
import java.util.UUID;

@Data
@RedisHash("Users")
public class Users {

    private UUID id;

    private String firstName;

    private String lastName;

    private Date birth;

    private Date createdDate;

    private Date eventDate;

    private String event;

}
