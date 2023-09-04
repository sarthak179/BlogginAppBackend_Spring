package com.example.BloggingAppAPI.users;

import com.example.BloggingAppAPI.JpaTestConfig;
import com.example.BloggingAppAPI.users.DTOs.CreateUserRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase()
public class UsersServiceTest {

    @Autowired
    UsersService usersService;

    @Test
    void can_create_users() {
        var user = usersService.createUser(new CreateUserRequestDTO("john","pass123","john@gmail.com"));
        Assertions.assertNotNull(user);
        Assertions.assertEquals("john", user.getUsername());
    }
}
