package com.example.BloggingAppAPI.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UsersRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void can_create_users() {
        var user = UserEntity.builder()
                .username("sarthak")
                .email("sarthak@gmail.com")
                .build();
        userRepository.save(user);
    }

    @Test
    void can_find_users() {
        var user = UserEntity.builder()
                .username("sarthak")
                .email("sarthak@gmail.com")
                .build();
        userRepository.save(user);
        var users = userRepository.findAll();
        Assertions.assertEquals(1, users.size());
    }
}
