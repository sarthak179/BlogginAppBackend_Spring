package com.example.BloggingAppAPI.users;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    Long id;

    @Column(nullable = false)
    @NonNull
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    @Column(nullable = true)
    private String bio;

    @Column(nullable = true)
    private String image;
}
