package com.example.BloggingAppAPI.users.DTOs;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateuserResponseDTO {

    Long id;
    private String username;

    private String email;

    private String bio;

    private String image;

    private String token;
}
