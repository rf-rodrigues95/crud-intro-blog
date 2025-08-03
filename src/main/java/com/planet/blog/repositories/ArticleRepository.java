package com.planet.blog.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planet.blog.domain.article.Article;

public interface ArticleRepository extends JpaRepository<Article, UUID>{
    Optional<Article> findByTitle(String title);
}
