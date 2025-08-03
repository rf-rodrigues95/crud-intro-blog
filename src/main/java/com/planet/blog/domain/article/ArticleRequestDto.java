package com.planet.blog.domain.article;

public record ArticleRequestDto(String title, String content, String tags, Long publishedAt) {

}
