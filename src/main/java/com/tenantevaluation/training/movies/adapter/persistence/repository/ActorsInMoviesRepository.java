package com.tenantevaluation.training.movies.adapter.persistence.repository;

import com.tenantevaluation.training.movies.adapter.persistence.model.ActorsInMoviesEntity;
import com.tenantevaluation.training.movies.adapter.persistence.model.ActorsMoviesCompositeKey;
import com.tenantevaluation.training.movies.adapter.persistence.model.GenreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigInteger;

public interface ActorsInMoviesRepository extends CrudRepository<ActorsInMoviesEntity, ActorsMoviesCompositeKey> {

}
