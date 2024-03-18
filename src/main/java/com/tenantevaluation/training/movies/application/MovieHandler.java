package com.tenantevaluation.training.movies.application;

import com.tenantevaluation.training.movies.domain.movie.Genre;
import com.tenantevaluation.training.movies.domain.movie.Movie;
import com.tenantevaluation.training.movies.domain.port.service.AuthorService;
import com.tenantevaluation.training.movies.domain.port.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieHandler {

    private final MovieService movieService;
    private final AuthorService authorService;

    public List<Movie> getMoviesByGenre(Genre genre, PageRequest pageRequest) {
        return movieService.getMoviesByGenre(genre, pageRequest).join();
    }

    public List<Movie> getMoviesByAuthorName(String authorName, int page, int size) {
        var pageRequest = PageRequest.of(page,size);

        return authorService.getAuthorByName(authorName, page, size)
            .thenApply(authors -> authors.stream()
                .map(author -> movieService.getMoviesByAuthor(author.getId(), pageRequest).join())
                .collect(Collectors.toList())).join()
            .stream().flatMap(List::stream)
            .distinct()
            .limit(size)
            .collect(Collectors.toUnmodifiableSet()).stream().toList();

    }

    public Movie addMovie(Movie movie) {
        return movieService.addMovie(movie).join();
    }

    public Movie updateMovie(BigInteger id, Movie movie) {
        return movieService.updateMovie(id, movie).join();
    }

    public void deleteMovie(BigInteger id) {
        movieService.deleteMovie(id);
    }

    public  Movie getMovie(BigInteger id){
        return movieService.getMovie(id).join();
    }


}
