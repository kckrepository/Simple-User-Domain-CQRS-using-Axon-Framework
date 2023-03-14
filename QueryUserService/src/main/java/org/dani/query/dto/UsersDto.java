package org.dani.query.dto;

import lombok.Data;
import org.dani.query.entity.Users;

import java.util.List;

@Data
public class UsersDto {

    private List<Users> users;

}
