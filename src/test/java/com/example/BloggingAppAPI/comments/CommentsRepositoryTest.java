package com.example.BloggingAppAPI.comments;

import com.example.BloggingAppAPI.articles.ArticleEntity;
import com.example.BloggingAppAPI.articles.ArticlesRepository;
import com.example.BloggingAppAPI.users.UserEntity;
import com.example.BloggingAppAPI.users.UserRepository;
import com.example.BloggingAppAPI.users.UsersRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class CommentsRepositoryTest {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private ArticlesRepository articlesRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void can_create_comment() {
        var user = UserEntity.builder()
                .username("sarthak")
                .email("sarthak@gmail.com")
                .build();
        userRepository.save(user);

        var article = ArticleEntity.builder()
                .title("article 1")
                .slug("article 1 slug")
                .subtitle("article 1 subtitle")
                .body("article body")
                .author(user).build();
        articlesRepository.save(article);

        var comment = CommentEntity.builder()
                .body("article 1 comment")
                .author(user)
                .article(article).build();

        commentsRepository.save(comment);
    }

    @Test
    void get_all_comments() {
        var user = UserEntity.builder()
                .username("sarthak")
                .email("sarthak@gmail.com")
                .build();
        userRepository.save(user);

        var article = ArticleEntity.builder()
                .title("article 1")
                .slug("article 1 slug")
                .subtitle("article 1 subtitle")
                .body("article body")
                .author(user).build();
        articlesRepository.save(article);

        var comment = CommentEntity.builder()
                .body("article 1 comment 1")
                .author(user)
                .article(article).build();

        commentsRepository.save(comment);

        var comment2 = CommentEntity.builder()
                .body("article 1 comment 2")
                .author(user)
                .article(article).build();

        commentsRepository.save(comment2);


        var comments = commentsRepository.findAll();

        assert comments.size() == 2;
    }
}
