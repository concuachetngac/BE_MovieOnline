package com.movie.movieonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.movieonline.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    
}
