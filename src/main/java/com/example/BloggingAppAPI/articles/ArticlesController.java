package com.example.BloggingAppAPI.articles;

import com.example.BloggingAppAPI.users.UserEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticlesController {
    private ArticlesService articlesService;
    public ArticlesController(@Autowired ArticlesService articlesService) {
        this.articlesService = articlesService;
    }
        @GetMapping("")
        public String getArticles() {
            return "get all articles";
        }

        @GetMapping("/{id}")
        public String getArticleById(@PathVariable("id") String id) {
            return "article by id";
        }

        @PostMapping("")
        public String createArticle(
                @AuthenticationPrincipal UserEntity userEntity
                ) {

            return "create article called by " + userEntity.getUsername();
        }
}
