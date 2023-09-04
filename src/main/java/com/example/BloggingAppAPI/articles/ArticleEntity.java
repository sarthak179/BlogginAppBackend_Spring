package com.example.BloggingAppAPI.articles;

import com.example.BloggingAppAPI.users.UserEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Articles")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    Long id;

    @NonNull
    @Column(length = 100)
    private String title;

    @Column(unique = true)
    private String slug;

    private String subtitle;

    @NonNull
    private String body;

    @CreatedDate
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    private UserEntity author;
}
