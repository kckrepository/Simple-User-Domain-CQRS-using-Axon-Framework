package org.dani.query.dto;

import lombok.Data;
import org.dani.query.entity.ActiveUsers;

import java.util.List;

@Data
public class ActiveUsersDto {
    private List<ActiveUsers> activeUsers;
}
