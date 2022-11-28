package com.movie.movieonline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.movieonline.domain.Genre;
import com.movie.movieonline.exception.ResourceNotFoundException;
import com.movie.movieonline.repository.GenreRepository;
import com.movie.movieonline.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;

    @Autowired
    GenreServiceImpl(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @Override
    public Boolean checkGenre(Long genreId) {
        try{
            genreRepository.findById(genreId).orElseThrow(() -> 
                new ResourceNotFoundException("Book", "Id", genreId));
            return true;
        } catch(ResourceNotFoundException e) {
            return false;
        }
    }

    @Override
    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre getGenreById(Long genreId) {
        return genreRepository.findById(genreId).orElseThrow(() -> 
            new ResourceNotFoundException("Book", "Id", genreId));
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public void addAllGenres(List<Genre> listGenres) {
        for(Genre genre:listGenres){
            if(checkGenre(genre.getId())==false){
                genreRepository.save(genre);
            }
        }
    }
    
}
