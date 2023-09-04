package com.example.BloggingAppAPI.articles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticlesRepository extends JpaRepository<ArticleEntity, Long> {
    List<ArticleEntity> findAll();

    ArticleEntity findBySlug(String slug);
}
