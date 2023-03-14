package org.dani.query.dto;

import lombok.Data;
import org.dani.query.entity.ActiveUsers;
import org.dani.query.entity.NonActiveUsers;

import java.util.List;

@Data
public class NonActiveUsersDto {
    private List<NonActiveUsers> nonActiveUsers;
}
