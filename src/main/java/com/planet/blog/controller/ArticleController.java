package com.planet.blog.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planet.blog.domain.article.Article;
import com.planet.blog.domain.article.ArticleRequestDto;
import com.planet.blog.service.ArticleService;
import com.planet.blog.utils.Result;

@RestController
@RequestMapping("/blog/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseEntity<Article> create(@RequestBody ArticleRequestDto requestDto) {
        var res = this.articleService.createArticle(requestDto);
        return toResponseEntity(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody ArticleRequestDto requestDto) {
        var res = this.articleService.updateArticle(id, requestDto);
        return toResponseEntity(res);   
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> get(@PathVariable UUID id) {
        var res = this.articleService.getArticle(id);
        return toResponseEntity(res);   
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        var res = this.articleService.deleteArticle(id);
        return toResponseEntity(res);
    }

    private <T> ResponseEntity<T> toResponseEntity(Result<T> result) {
        if (result.isOK()) {
            if (result.value() == null)
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok(result.value());
        }

        return switch (result.error()) {
            case NOT_FOUND -> ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            case CONFLICT -> ResponseEntity.status(HttpStatus.CONFLICT).build();
            case FORBIDDEN -> ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            case BAD_REQUEST -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            case INTERNAL_ERROR -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            case NOT_IMPLEMENTED -> ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        };
    }

/*     @PutMapping("/{id}")
    public ResponseEntity<Article> update(@PathVariable UUID id, @RequestBody ArticleRequestDto requestDto) {

    } */
}
