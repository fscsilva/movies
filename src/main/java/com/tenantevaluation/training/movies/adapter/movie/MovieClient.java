package com.tenantevaluation.training.movies.adapter.movie;

import com.tenantevaluation.training.movies.adapter.persistence.model.GenreEntity;
import com.tenantevaluation.training.movies.adapter.persistence.model.MovieEntity;
import com.tenantevaluation.training.movies.adapter.persistence.repository.MovieRepository;
import com.tenantevaluation.training.movies.domain.movie.Author;
import com.tenantevaluation.training.movies.domain.movie.Genre;
import com.tenantevaluation.training.movies.domain.movie.Movie;
import com.tenantevaluation.training.movies.domain.port.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class MovieClient implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public CompletableFuture<List<Movie>> getMoviesByGenre(Genre genre, PageRequest pageRequest) {
        return CompletableFuture.supplyAsync(() ->  movieRepository.findAllByGenre_Id(genre.getGenreId(), pageRequest)
            .stream()
            .map(movieEntity -> Movie.builder()
                .id(movieEntity.getId())
                .title(movieEntity.getTitle())
                .year(LocalDate.parse(movieEntity.getYear(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .author(Author.builder().id(movieEntity.getAuthor()).build())
                .genre(Genre.builder().genreId(movieEntity.getGenre().getId()).description(movieEntity.getGenre().getDescription()).build())
                .build())
            .collect(Collectors.toList()));
    }

    @Override
    public CompletableFuture<List<Movie>> getMoviesByAuthor(BigInteger id, PageRequest pageRequest) {
        return CompletableFuture.supplyAsync(() -> movieRepository.findAllByAuthor(id, pageRequest)
            .stream()
            .map(movieEntity -> Movie.builder()
                .id(movieEntity.getId())
                .year(LocalDate.parse(movieEntity.getYear(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .author(Author.builder().id(movieEntity.getAuthor()).build())
                .genre(Genre.builder().genreId(movieEntity.getGenre().getId()).description(movieEntity.getGenre().getDescription()).build())
                .title(movieEntity.getTitle())
                .build())
            .collect(Collectors.toList()));
    }

    @Override
    public CompletableFuture<Movie> addMovie(Movie movie) {
        var entity = new MovieEntity();
        var genre = new GenreEntity(movie.getGenre().getGenreId(), movie.getGenre().getDescription());
        entity.setAuthor(movie.getAuthor().getId());
        entity.setGenre(genre);
        entity.setYear(String.valueOf(movie.getYear().getYear()));
        entity.setTitle(movie.getTitle());

        return CompletableFuture.supplyAsync(() ->  movieRepository.save(entity))
            .thenApply(movieEntity -> Movie.builder()
                .year(LocalDate.of(Integer.parseInt(movieEntity.getYear()), 1,1) )
                .id(movieEntity.getId())
                .author(Author.builder().id(movieEntity.getAuthor()).build())
                .genre(Genre.builder().genreId(movieEntity.getGenre().getId()).description(movieEntity.getGenre().getDescription()).build())
                .title(movieEntity.getTitle())
                .build());
    }

    @Override
    public CompletableFuture<Movie> updateMovie(BigInteger id, Movie movie) {

        var entity = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("movie not found"));

        var genre = Optional.ofNullable(movie.getGenre())
            .map(newGenre -> new GenreEntity(movie.getGenre().getGenreId(), movie.getGenre().getDescription()))
            .orElseGet(entity::getGenre);
        var year = Optional.ofNullable(movie.getYear())
            .map(newYear -> String.valueOf(movie.getYear().getYear()))
            .orElseGet(() -> entity.getYear().substring(0,4));
        var author = Optional.ofNullable(movie.getAuthor())
            .map(newAuthor -> movie.getAuthor().getId())
            .orElseGet(entity::getAuthor);
        var title = Optional.ofNullable(movie.getTitle())
            .orElseGet(entity::getTitle);

        entity.setAuthor(author);
        entity.setGenre(genre);
        entity.setYear(year);
        entity.setTitle(title);

        return CompletableFuture.supplyAsync(() ->  movieRepository.save(entity))
            .thenApply(movieEntity -> Movie.builder()
                .year(LocalDate.of(Integer.parseInt(movieEntity.getYear()), 1,1) )
                .id(movieEntity.getId())
                .author(Author.builder().id(movieEntity.getAuthor()).build())
                .genre(Genre.builder().genreId(movieEntity.getGenre().getId()).description(movieEntity.getGenre().getDescription()).build())
                .title(movieEntity.getTitle())
                .build());
    }

    @Override
    public void deleteMovie(BigInteger id) {
        movieRepository.deleteById(id);
    }

    @Override
    public CompletableFuture<Movie> getMovie(BigInteger id) {
        return CompletableFuture.supplyAsync(() ->  movieRepository.findById(id))
            .thenApply(entity -> entity.orElseThrow(() -> new MovieNotFoundException("movie not found")))
            .thenApply(movieEntity -> Movie.builder()
                .id(movieEntity.getId())
                .year(LocalDate.parse(movieEntity.getYear(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .author(Author.builder().id(movieEntity.getAuthor()).build())
                .genre(Genre.builder().genreId(movieEntity.getGenre().getId()).description(movieEntity.getGenre().getDescription()).build())
                .title(movieEntity.getTitle())
                .build());
    }

}