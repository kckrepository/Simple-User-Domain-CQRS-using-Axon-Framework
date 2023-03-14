package org.dani.query.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dani.query.dto.MessageDto;
import org.dani.query.entity.ActiveUsers;
import org.dani.query.entity.NonActiveUsers;
import org.dani.query.entity.Users;
import org.dani.query.repository.ActiveUsersRepository;
import org.dani.query.repository.NonActiveUsersRepository;
import org.dani.query.repository.UsersRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ProcessorHandler {

    private final UsersRepository usersRepository;
    private final ActiveUsersRepository activeUsersRepository;

    private final NonActiveUsersRepository nonActiveUsersRepository;

    @KafkaListener(topics = "user", groupId = "user-consumer-group")
    public void consume(MessageDto message) {
        log.info(String.format("Message received -> %s", message));

        if (message.getEvent().equalsIgnoreCase("UserCreatedEvent")) {
            Users user = new Users();
            user.setId(message.getId());
            user.setBirth(message.getBirth());
            user.setFirstName(message.getFirstName());
            user.setLastName(message.getLastName());
            user.setEvent("UserCreatedEvent");
            user.setCreatedDate(message.getCreated());
            user.setEventDate(message.getCreated());
            usersRepository.save(user);
        }

        if (message.getEvent().equalsIgnoreCase("UserActivatedEvent")) {
            usersRepository.findById(message.getId())
                    .ifPresent(users -> {
                        users.setEventDate(message.getCreated());
                        users.setEvent("UserActivatedEvent");
                        usersRepository.save(users);

                        ActiveUsers activeUsers = new ActiveUsers();
                        activeUsers.setId(users.getId());
                        activeUsers.setBirth(users.getBirth());
                        activeUsers.setFirstName(users.getFirstName());
                        activeUsers.setLastName(users.getLastName());
                        activeUsers.setEvent("UserActivatedEvent");
                        activeUsers.setEventDate(message.getCreated());
                        activeUsersRepository.save(activeUsers);
                    });
        }

        if (message.getEvent().equalsIgnoreCase("UserDeactivatedEvent")) {
            usersRepository.findById(message.getId())
                    .ifPresent(users -> {
                        users.setEventDate(message.getCreated());
                        users.setEvent("UserDeactivatedEvent");
                        usersRepository.save(users);

                        NonActiveUsers nonActiveUsers = new NonActiveUsers();
                        nonActiveUsers.setId(users.getId());
                        nonActiveUsers.setBirth(users.getBirth());
                        nonActiveUsers.setFirstName(users.getFirstName());
                        nonActiveUsers.setLastName(users.getLastName());
                        nonActiveUsers.setEventDate(message.getCreated());
                        nonActiveUsers.setEvent("UserDeactivatedEvent");
                        nonActiveUsersRepository.save(nonActiveUsers);
                    });

            activeUsersRepository.findById(message.getId())
                    .ifPresent(activeUsers -> {
                        activeUsersRepository.delete(activeUsers);
                    });
        }
    }

}
