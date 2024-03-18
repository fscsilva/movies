package com.tenantevaluation.training.movies.adapter.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActorsMoviesCompositeKey implements Serializable {

    private BigInteger movie_id;
    private BigInteger actor_id;

}
