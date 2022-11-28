package com.movie.movieonline.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.movieonline.domain.Genre;
import com.movie.movieonline.domain.Movie;
import com.movie.movieonline.service.GenreService;
import com.movie.movieonline.service.MovieService;
import com.movie.movieonline.util.MessageResponse;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private MovieService movieService;
    private GenreService genreService;

    MovieController(MovieService movieService, GenreService genreService){
        super();
        this.movieService=movieService;
        this.genreService=genreService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie){
        return new ResponseEntity<Movie>(movieService.addMovie(movie), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("save/all/{is_movie}")
    public ResponseEntity<?> saveAllMovies(@RequestBody List<Movie> moviesList, @PathVariable("is_movie") int isMovie){
        if(isMovie != 0 && isMovie!=1){
            return new ResponseEntity<MessageResponse>(new MessageResponse("Some error !!!"), HttpStatus.BAD_REQUEST);  
        }
        for(Movie movie:moviesList){
            List<Long> idList = movie.getGenre_ids();
            List<Genre> genresList = new ArrayList<>();
            for(long id:idList){
                genresList.add(genreService.getGenreById(id));
            }
            movie.setGenres(genresList);
            movie.setIsMovie(isMovie);
            movieService.addMovie(movie);
        }
        return new ResponseEntity<List<Movie>>(moviesList, HttpStatus.CREATED);
    }

    @GetMapping("all")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") long movieId){
        movieService.deleteMovie(movieId);
        return new ResponseEntity<String> ("Delete Movie successfully!.", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable long id){
        Movie movie = movieService.getMovieById(id);
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }
}
