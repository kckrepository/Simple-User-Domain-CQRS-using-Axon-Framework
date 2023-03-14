package org.dani.query.entity;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;
import java.util.UUID;

@Data
@RedisHash("NonActiveUsers")
public class NonActiveUsers {
    private UUID id;

    private String firstName;

    private String lastName;

    private Date birth;

    private Date eventDate;

    private String event;
}
