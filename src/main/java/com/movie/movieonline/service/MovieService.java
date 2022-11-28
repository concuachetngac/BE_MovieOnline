package com.movie.movieonline.service;

import java.util.List;

import com.movie.movieonline.domain.Movie;

public interface MovieService {
    Movie addMovie(Movie movie);
    void deleteMovie(Long movieId);
    List<Movie> getAllMovies();
    Movie updateMovie(Movie movie, Long movieId);
    Movie getMovieById(Long movieId);
}
