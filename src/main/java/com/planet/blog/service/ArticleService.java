package com.planet.blog.service;

import static com.planet.blog.utils.Result.error;
import static com.planet.blog.utils.Result.ErrorCode.BAD_REQUEST;
import static com.planet.blog.utils.Result.ErrorCode.CONFLICT;
import static com.planet.blog.utils.Result.ErrorCode.INTERNAL_ERROR;
import static com.planet.blog.utils.Result.ErrorCode.NOT_FOUND;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planet.blog.domain.article.Article;
import com.planet.blog.domain.article.ArticleRequestDto;
import com.planet.blog.repositories.ArticleRepository;
import com.planet.blog.utils.Result;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository repository;

    public Result<Article> createArticle(ArticleRequestDto data) {
        try {
            if(data.title() == null)
                return error(BAD_REQUEST);

            if (repository.findByTitle(data.title()).isPresent())
                return error(CONFLICT);

            Article newArticle = new Article();
            newArticle.setTitle(data.title());
            newArticle.setContent(data.content());
            newArticle.setTags(data.tags());
            newArticle.setPublishedAt(new Date(data.publishedAt()));
            repository.save(newArticle);

            return Result.ok(newArticle);
        } catch (Exception e) {
            return error(INTERNAL_ERROR);
        }
    }

    public Result<Void> updateArticle(UUID id, ArticleRequestDto data) {
        try {
            if(id == null || data.title() == null)
                return error(BAD_REQUEST);

            Optional<Article> articleOpt = repository.findById(id);
            if (!articleOpt.isPresent())
                return error(NOT_FOUND);

            Article article = articleOpt.get();
            article.setTitle(data.title());
            article.setContent(data.content());
            article.setTags(data.tags());
            article.setPublishedAt(new Date(data.publishedAt()));

            repository.save(article);

            return Result.ok();
        } catch (Exception e) {
            return error(INTERNAL_ERROR);
        }
    }

    public Result<Article> getArticle(UUID id) {
        try {
            if (id == null)
                return error(BAD_REQUEST);

            Optional<Article> articleOpt = repository.findById(id);
            if (!articleOpt.isPresent())
                return error(NOT_FOUND);

            Article article = articleOpt.get();
            return Result.ok(article);
        } catch (Exception e) {
            return error(INTERNAL_ERROR);
        }
    }

    public Result<Void> deleteArticle(UUID id) {
        if (!repository.existsById(id))
            return error(NOT_FOUND);

        try {
            repository.deleteById(id);
            return Result.ok();
        } catch (Exception e) {
            return error(INTERNAL_ERROR);
        }         
    }
}
