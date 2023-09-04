package com.example.BloggingAppAPI.articles;

import com.example.BloggingAppAPI.articles.DTOs.CreateArticleRequestDTO;
import com.example.BloggingAppAPI.users.UserRepository;
import com.example.BloggingAppAPI.users.UsersService;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesService {
    private ArticlesRepository articlesRepository;
    private UserRepository userRepository;
    public ArticlesService(@Autowired ArticlesRepository articlesRepository,
                           @Autowired UserRepository userRepository) {
        this.articlesRepository = articlesRepository;
        this.userRepository = userRepository;
    }

    public List<ArticleEntity> getAllArticles() {
        return articlesRepository.findAll();
    }

    public ArticleEntity findArticleBySlug(String slug) {
        var article = articlesRepository.findBySlug(slug);
        if(article == null) {
            throw new ArticleNotFoundException(slug);
        }
        return article;
    }

    public ArticleEntity createArticle(CreateArticleRequestDTO createArticleRequestDTO, String authorId) {
        var user = userRepository.findByUsername(authorId);
        if(user == null) {
            throw new UsersService.UserNotFoundException(authorId);
        }
        return articlesRepository.save(ArticleEntity.builder()
                .title(createArticleRequestDTO.getTitle())
                .body(createArticleRequestDTO.getBody())
                .slug(createArticleRequestDTO.getTitle())
                .author(user).build());
    }

    static class ArticleNotFoundException extends IllegalArgumentException {
        public ArticleNotFoundException(String slug) {
            super("Article "+slug+" not found");
        }
    }
}
