package com.tenantevaluation.training.movies.adapter.author;

import com.tenantevaluation.training.movies.domain.movie.Author;
import com.tenantevaluation.training.movies.domain.port.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class AuthorClient implements AuthorService {

    @Qualifier("author-rest")
    private final RestTemplate restTemplate;

    @Value("${author-service.getAuthorByIdUrl}")
    private String getAuthorByIdUrl;

    @Value("${author-service.getAuthorByNameUrl}")
    private String getAuthorByNameUrl;

    @Override
    public CompletableFuture<Author> getAuthor(BigInteger id) {
        var queryUrl = UriComponentsBuilder
            .fromHttpUrl(getAuthorByIdUrl)
            .buildAndExpand(id)
            .toUriString();

        return CompletableFuture.completedFuture(restTemplate.getForObject(queryUrl, Author.class))
            .thenApply(response -> response);
    }

    @Override
    public CompletableFuture<List<Author>> getAuthorByName(String name, int page, int size) {
        var queryUrl = UriComponentsBuilder
            .fromHttpUrl(getAuthorByNameUrl)
            .buildAndExpand(name,page,size)
            .toUriString();

        return CompletableFuture.completedFuture(restTemplate.getForObject(queryUrl, Author[].class))
            .thenApply(response -> Arrays.stream(response).toList());

    }
}