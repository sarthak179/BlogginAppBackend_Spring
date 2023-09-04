package com.example.BloggingAppAPI.articles;

import com.example.BloggingAppAPI.users.UserEntity;
import com.example.BloggingAppAPI.users.UserRepository;
import com.example.BloggingAppAPI.users.UsersRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class ArticlesRepositoryTest {

    @Autowired
    private ArticlesRepository articlesRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void can_create_Articles() {
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
    }

    @Test
    void get_all_articles() {
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
        var articles = articlesRepository.findAll();

        assert articles.size() == 1;
    }
}
