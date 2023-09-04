package com.example.BloggingAppAPI.users;

import com.auth0.jwt.JWT;
import com.example.BloggingAppAPI.Commons.DTOs.ErrorResponse;
import com.example.BloggingAppAPI.Security.JWTService;
import com.example.BloggingAppAPI.users.DTOs.CreateUserRequestDTO;
import com.example.BloggingAppAPI.users.DTOs.CreateuserResponseDTO;
import com.example.BloggingAppAPI.users.DTOs.LoginUserRequest;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;
    public UsersController(@Autowired UsersService usersService, ModelMapper modelMapper, JWTService jwtService) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    @PostMapping("")
    ResponseEntity<CreateuserResponseDTO> signupUser(@RequestBody CreateUserRequestDTO createUserRequestDTO) {
        UserEntity savedUser = usersService.createUser(createUserRequestDTO);
        URI savedUserURI = URI.create("/users/"+savedUser.getId());
        var savedUserResponse = modelMapper.map(savedUser, CreateuserResponseDTO.class);
        savedUserResponse.setToken(
                jwtService.createJwt(savedUser.getId())
        );
        return ResponseEntity.created(savedUserURI)
                .body(savedUserResponse);
    }

    @PostMapping("/login")
    ResponseEntity<CreateuserResponseDTO> loginUser(@RequestBody LoginUserRequest loginUserRequest) throws UsersService.InvalidCredentialsException {
        UserEntity loggedInUser = usersService.loginUser(loginUserRequest.getUsername(),
                                        loginUserRequest.getPassword());
        var userResponse = modelMapper.map(loggedInUser, CreateuserResponseDTO.class);
        userResponse.setToken(
                jwtService.createJwt(loggedInUser.getId())
        );
        return ResponseEntity.ok(userResponse);
    }

    @ExceptionHandler({
            UsersService.UserNotFoundException.class,
            UsersService.InvalidCredentialsException.class
    })
    ResponseEntity<ErrorResponse> handleUserException(Exception ex) {

        String message;
        HttpStatus httpStatus;

        if(ex instanceof UsersService.UserNotFoundException) {
            httpStatus = HttpStatus.NOT_FOUND;
            message = ex.getMessage();
        }
        else if(ex instanceof UsersService.InvalidCredentialsException) {
            message = ex.getMessage();
            httpStatus = HttpStatus.UNAUTHORIZED;
        }
        else {
            message = "Something went wrong";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(httpStatus).body(ErrorResponse.builder().message(message).build());
    }


}
