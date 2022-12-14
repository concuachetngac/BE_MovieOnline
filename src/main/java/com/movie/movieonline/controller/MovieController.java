package com.movie.movieonline.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import com.movie.movieonline.domain.Comment;
import com.movie.movieonline.domain.Genre;
import com.movie.movieonline.domain.Movie;
import com.movie.movieonline.service.GenreService;
import com.movie.movieonline.service.MovieService;
import com.movie.movieonline.util.TVSeriesRequest;

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
    @PostMapping("save/all/movie")
    public ResponseEntity<?> saveAllMovies(@RequestBody List<Movie> moviesList){
        for(Movie movie:moviesList){
            List<Long> idList = movie.getGenre_ids();
            List<Genre> genresList = new ArrayList<>();
            for(long id:idList){
                genresList.add(genreService.getGenreById(id));
            }
            movie.setGenres(genresList);
            movie.setIsMovie(1);
            movieService.addMovie(movie);
        }
        return new ResponseEntity<List<Movie>>(moviesList, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("save/all/tv")
    public ResponseEntity<?> saveAllTV(@RequestBody List<TVSeriesRequest> moviesList){
        for(TVSeriesRequest tv:moviesList){
            List<Long> idList = tv.getGenre_ids();
            List<Genre> genresList = new ArrayList<>();
            for(long id:idList){
                genresList.add(genreService.getGenreById(id));
            }
            Movie movie = new Movie();
            movie.setBackdrop_path(tv.getBackdrop_path());
            movie.setPoster_path(tv.getPoster_path());
            movie.setId(tv.getId());
            movie.setTitle(tv.getName());
            movie.setVote_average(tv.getVote_average());
            movie.setRelease_date(tv.getFirst_air_date());
            movie.setOverview(tv.getOverview());
            movie.setGenres(genresList);
            movie.setIsMovie(0);
            movieService.addMovie(movie);
        }
        return new ResponseEntity<List<TVSeriesRequest>>(moviesList, HttpStatus.CREATED);
    }

    @GetMapping("all")
    public List<Movie> getAllMovies(){
        List<Movie> myList =movieService.getAllMovies();
        
        for(Movie m:myList){
            List<Comment> listComment = m.getComments();
            Collections.sort(listComment, new Comparator<Comment>() {
                public int compare(Comment o1, Comment o2) {
                    if (o1.getTime() == null || o2.getTime() == null)
                      return 0;
                    return o2.getTime().compareTo(o1.getTime());
                }
              });
              m.setComments(listComment);
        }
        return myList;
    }

    @GetMapping("find/{movieName}")
    public List<Movie> findMovieByName(@PathVariable("movieName") String movieName){
        List<Movie> myList =movieService.getAllMovies();
        List<Movie> findList = new ArrayList<>();
        for(Movie m:myList){
            if(m.getTitle().toLowerCase().contains(movieName)){
                findList.add(m);
            }
        }
        return findList;
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
        List<Comment> listComment = movie.getComments();
        Collections.sort(listComment, new Comparator<Comment>() {
            public int compare(Comment o1, Comment o2) {
                if (o1.getTime() == null || o2.getTime() == null)
                  return 0;
                return o2.getTime().compareTo(o1.getTime());
            }
          });
        movie.setComments(listComment);
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }
}
