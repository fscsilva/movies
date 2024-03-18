package com.tenantevaluation.training.movies.adapter.movie;

import com.tenantevaluation.training.movies.application.MovieHandler;
import com.tenantevaluation.training.movies.domain.movie.Genre;
import com.tenantevaluation.training.movies.domain.movie.Movie;
import com.tenantevaluation.training.movies.domain.port.api.MovieAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MovieController implements MovieAPI {

    private final MovieHandler movieHandler;

    @Override
    public ResponseEntity<List<Movie>> getMoviesByAuthorName(String name, int page, int size) {
        return ResponseEntity.ok(movieHandler.getMoviesByAuthorName(name, page, size));
    }

    @Override
    public ResponseEntity<List<Movie>> getMoviesByGenre(BigInteger id, int page, int size) {
        return ResponseEntity.ok(movieHandler.getMoviesByGenre(Genre.builder().genreId(id).build(), PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<Movie> addMovie(Movie movie) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieHandler.addMovie(movie));
    }

    @Override
    public ResponseEntity<Movie> updateMovie(BigInteger id, Movie movie) {
        return ResponseEntity.ok(movieHandler.updateMovie(id, movie));
    }

    @Override
    public ResponseEntity<Void> deleteMovie(BigInteger id) {
        movieHandler.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Movie> getMovie(BigInteger id) {
        return ResponseEntity.ok(movieHandler.getMovie(id));
    }

}
