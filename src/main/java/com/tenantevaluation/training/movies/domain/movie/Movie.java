package com.tenantevaluation.training.movies.domain.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.math.BigInteger;
import java.time.LocalDate;

@Jacksonized
@Builder
@Getter
public class Movie {

    @JsonProperty
    private final BigInteger id;
    @JsonProperty
    @NotNull(message = "The title is required.")
    private final String title;
    @JsonProperty
    @NotNull(message = "The year is required.")
    @PastOrPresent(message = "The year must be in the past or present.")
    private final LocalDate year;
    @JsonProperty
    @NotNull(message = "The genre is required.")
    private final Genre genre;
    @JsonProperty
    @NotNull(message = "The author is required.")
    private final Author author;
    //@JsonProperty
    //private String imageUrl;
    //@JsonProperty
    //private MultipartFile image;

}
