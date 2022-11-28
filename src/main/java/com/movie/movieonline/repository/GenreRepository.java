package com.movie.movieonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.movieonline.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    
}
