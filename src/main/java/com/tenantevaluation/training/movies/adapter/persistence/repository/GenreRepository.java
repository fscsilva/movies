package com.tenantevaluation.training.movies.adapter.persistence.repository;

import com.tenantevaluation.training.movies.adapter.persistence.model.GenreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigInteger;

@RepositoryRestResource(collectionResourceRel = "genre", path = "genres")
public interface GenreRepository extends CrudRepository<GenreEntity, BigInteger> {

}
