package org.dani.query.controller;

import lombok.AllArgsConstructor;
import org.dani.query.dto.ActiveUsersDto;
import org.dani.query.dto.NonActiveUsersDto;
import org.dani.query.dto.UsersDto;
import org.dani.query.repository.ActiveUsersRepository;
import org.dani.query.repository.NonActiveUsersRepository;
import org.dani.query.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UsersController {

    private final UsersRepository usersRepository;
    private final ActiveUsersRepository activeUsersRepository;

    private final NonActiveUsersRepository nonActiveUsersRepository;

    @GetMapping("/users")
    public ResponseEntity<UsersDto> listUsers() {
        UsersDto usersDto = new UsersDto();
        usersDto.setUsers(usersRepository.findAll());
        return new ResponseEntity<UsersDto>(usersDto, HttpStatus.OK);
    }

    @GetMapping("/active-users")
    public ResponseEntity<ActiveUsersDto> listActiveUsers() {
        ActiveUsersDto activeUsersDto = new ActiveUsersDto();
        activeUsersDto.setActiveUsers(activeUsersRepository.findAll());
        return new ResponseEntity<ActiveUsersDto>(activeUsersDto, HttpStatus.OK);
    }

    @GetMapping("/nonactive-users")
    public ResponseEntity<NonActiveUsersDto> listNonActiveUsers() {
        NonActiveUsersDto nonActiveUsersDto = new NonActiveUsersDto();
        nonActiveUsersDto.setNonActiveUsers(nonActiveUsersRepository.findAll());
        return new ResponseEntity<NonActiveUsersDto>(nonActiveUsersDto, HttpStatus.OK);
    }

}
