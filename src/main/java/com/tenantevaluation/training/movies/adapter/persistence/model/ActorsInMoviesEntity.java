package com.tenantevaluation.training.movies.adapter.persistence.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.math.BigInteger;

@Getter
@Setter
@Entity
@Table(name = "actors_in_movies")
@IdClass(ActorsMoviesCompositeKey.class)
@AllArgsConstructor
@NoArgsConstructor
public class ActorsInMoviesEntity {

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieEntity movie;

    @NonNull
    @Id
    @JoinColumn(name = "movie_id", nullable = false)
    private BigInteger movie_id;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "actor_id", nullable = false)
    private ActorEntity actor;

    @NonNull
    @Id
    @JoinColumn(name = "actor_id", nullable = false)
    private BigInteger actor_id;

}
