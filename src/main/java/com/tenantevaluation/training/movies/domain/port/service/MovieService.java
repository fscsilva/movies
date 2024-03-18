package com.tenantevaluation.training.movies.domain.port.service;

import com.tenantevaluation.training.movies.domain.movie.Genre;
import com.tenantevaluation.training.movies.domain.movie.Movie;
import org.springframework.data.domain.PageRequest;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MovieService {

    CompletableFuture<List<Movie>> getMoviesByGenre(Genre genre, PageRequest pageRequest);

    CompletableFuture<List<Movie>> getMoviesByAuthor(BigInteger id, PageRequest pageRequest);

    CompletableFuture<Movie> addMovie(Movie movie);

    CompletableFuture<Movie> updateMovie(BigInteger id, Movie movie);

    void deleteMovie(BigInteger id);

    CompletableFuture<Movie> getMovie(BigInteger id);
}
