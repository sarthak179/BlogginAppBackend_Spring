package com.example.BloggingAppAPI.users;

import com.example.BloggingAppAPI.Security.JWTService;
import com.example.BloggingAppAPI.users.DTOs.CreateUserRequestDTO;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.Optional;

@Service
public class UsersService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    public UsersService(@Autowired UserRepository userRepository,
                        @Autowired ModelMapper modelMapper,
                        @Autowired PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity createUser(CreateUserRequestDTO createUserRequestDTO) {
        var newUser = UserEntity.builder()
                .username(createUserRequestDTO.getUsername())
                .email(createUserRequestDTO.getEmail()).build();

        newUser.setPassword(passwordEncoder.encode(createUserRequestDTO.getPassword()));
        //UserEntity newUser = modelMapper.map(createUserRequestDTO, UserEntity.class);
        return userRepository.save(newUser);
    }

    public UserEntity getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public UserEntity loginUser(String username, String password) throws InvalidCredentialsException {
        var userEntity = userRepository.findByUsername(username);
        if(userEntity == null) {
            throw new UserNotFoundException(username);
        }
        var passMAtch = passwordEncoder.matches(password, userEntity.getPassword());
        if(!passMAtch) {
            throw new InvalidCredentialsException();
        }
        return userEntity;
    }

    public static class UserNotFoundException extends IllegalArgumentException {
        public UserNotFoundException(String userName) {
            super("user "+userName+" not found");
        }

        public UserNotFoundException(Long userId) {
            super("user with id: "+userId+" not found");
        }
    }

    public static class InvalidCredentialsException extends IllegalAccessException {
        public InvalidCredentialsException() {
            super("Inavlid username or password combination");
        }

    }
}
