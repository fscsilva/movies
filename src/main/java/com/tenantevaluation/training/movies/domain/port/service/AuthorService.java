package com.tenantevaluation.training.movies.domain.port.service;

import com.tenantevaluation.training.movies.domain.movie.Author;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AuthorService {

    @Retryable(retryFor = {RestClientException.class}, noRetryFor = {HttpClientErrorException.class},
        maxAttemptsExpression = "${rest-template.maximumRetries}",
        backoff = @Backoff(delayExpression = "${rest-template.fixedBackOffPeriod}"))
    CompletableFuture<Author> getAuthor(BigInteger id);

    @Retryable(retryFor = {RestClientException.class}, noRetryFor = {HttpClientErrorException.class},
        maxAttemptsExpression = "${rest-template.maximumRetries}",
        backoff = @Backoff(delayExpression = "${rest-template.fixedBackOffPeriod}"))
    CompletableFuture<List<Author>> getAuthorByName(String name, int page, int size);

}
