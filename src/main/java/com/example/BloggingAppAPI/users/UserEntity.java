package com.example.BloggingAppAPI.users;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    Long id;

    @Column(nullable = false)
    @NonNull
    private String username;

    @Column(nullable = false)
    @NonNull
    private String email;

    @Column(nullable = true)
    @NonNull
    private String bio;

    @Column(nullable = true)
    @NonNull
    private String image;
}
