package org.dani.command.controller;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.dani.command.command.ActiveUserCommand;
import org.dani.command.command.DeactivateUserCommand;
import org.dani.command.dto.ActivateUserDto;
import org.dani.command.dto.DeactivateUserDto;
import org.dani.command.dto.UserDto;
import org.dani.command.command.CreateUserCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class UserController {

    private final CommandGateway commandGateway;

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto newUser) {
        CreateUserCommand createUserCommand = CreateUserCommand.builder()
                .id(UUID.randomUUID())
                .firstName(newUser.getFirstName())
                        .lastName(newUser.getLastName())
                                .birth(newUser.getBirth())
                                        .created(new Date())
                                                .build();
        commandGateway.send(createUserCommand);
        return new ResponseEntity<UserDto>(newUser, HttpStatus.OK);
    }

    @PutMapping("/active")
    public ResponseEntity<ActivateUserDto> active(@Valid @RequestBody ActivateUserDto activateUserDto) {
        ActiveUserCommand activeUserCommand = ActiveUserCommand.builder()
                .id(UUID.fromString(activateUserDto.getId()))
                .updated(new Date())
                .build();
        commandGateway.send(activeUserCommand);
        return new ResponseEntity<ActivateUserDto>(activateUserDto, HttpStatus.OK);
    }

    @PutMapping("/deactive")
    public ResponseEntity<DeactivateUserDto> deactive(@Valid @RequestBody DeactivateUserDto deactivateUserDto) {
        DeactivateUserCommand deactivateUserCommand = DeactivateUserCommand.builder()
                .id(UUID.fromString(deactivateUserDto.getId()))
                .updated(new Date())
                .build();
        commandGateway.send(deactivateUserCommand);
        return new ResponseEntity<DeactivateUserDto>(deactivateUserDto, HttpStatus.OK);
    }

}
