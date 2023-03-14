package org.dani.query.entity;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;
import java.util.UUID;

@Data
@RedisHash("ActiveUsers")
public class ActiveUsers {
    private UUID id;

    private String firstName;

    private String lastName;

    private Date birth;

    private Date eventDate;

    private String event;
}
