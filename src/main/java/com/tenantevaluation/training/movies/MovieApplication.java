package com.tenantevaluation.training.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.tenantevaluation.training.movies.adapter.persistence.model")
@EnableJpaRepositories(basePackages = "com.tenantevaluation.training.movies.adapter.persistence.repository")
public class MovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }

}
