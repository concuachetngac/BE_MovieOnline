package com.movie.movieonline.service;

import java.util.List;

import com.movie.movieonline.domain.Genre;

public interface GenreService {
    Boolean checkGenre(Long genreId);
    Genre addGenre(Genre genre);
    Genre getGenreById(Long genreId);
    List<Genre> getAllGenres();
    void addAllGenres(List<Genre> listGenres);
}
