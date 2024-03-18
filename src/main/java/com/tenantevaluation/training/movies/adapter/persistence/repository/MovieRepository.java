package com.tenantevaluation.training.movies.adapter.persistence.repository;


import com.tenantevaluation.training.movies.adapter.persistence.model.MovieEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigInteger;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "movie", path = "movies")
public interface MovieRepository extends PagingAndSortingRepository<MovieEntity, BigInteger>, CrudRepository<MovieEntity, BigInteger> {

    @Operation(summary = "Get movies by author")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Movies retrieved", content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = MovieEntity.class)) }),
        @ApiResponse(responseCode = "500", description = "There was an error searching the Movies"),
        @ApiResponse(responseCode = "400", description = "Malformed request")})
    List<MovieEntity> findAllByAuthor(@Param("author_id") BigInteger authorId, Pageable pageable);

    @Operation(summary = "Get movies by genre")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Movies retrieved", content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = MovieEntity.class)) }),
        @ApiResponse(responseCode = "500", description = "There was an error searching the Movies"),
        @ApiResponse(responseCode = "400", description = "Malformed request")})
    List<MovieEntity> findAllByGenre_Id (@Param("genre_Id") BigInteger genreId, Pageable pageable);

}
