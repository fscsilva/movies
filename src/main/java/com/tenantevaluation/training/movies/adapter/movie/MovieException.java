package com.tenantevaluation.training.movies.adapter.movie;

import jakarta.validation.ValidationException;

public class MovieException extends ValidationException {

    public MovieException(String message) {
        super(message);
    }
}
