package com.movie.movieonline.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.movieonline.domain.Genre;
import com.movie.movieonline.service.GenreService;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    private GenreService genreService;

    GenreController(GenreService genreService){
        super();
        this.genreService=genreService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("save")
    public ResponseEntity<Genre> saveGenre(@RequestBody Genre genre){
        return new ResponseEntity<Genre>(genreService.addGenre(genre), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("save/all")
    public ResponseEntity<List<Genre>> saveAllGenre(@RequestBody List<Genre> genreList){
        for(Genre genre:genreList){
            if(!genreService.checkGenre(genre.getId())){
                genreService.addGenre(genre);
            }
        }
        return new ResponseEntity<List<Genre>> (genreList, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Genre> getAllGenres(){
        return genreService.getAllGenres();
    }

    @GetMapping("{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable long id){
        Genre genre = genreService.getGenreById(id);
        return new ResponseEntity<Genre>(genre, HttpStatus.OK);
    }
}
