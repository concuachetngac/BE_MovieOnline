package com.movie.movieonline.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.movieonline.domain.Comment;
import com.movie.movieonline.domain.Movie;
import com.movie.movieonline.domain.WishList;
import com.movie.movieonline.exception.ResourceNotFoundException;
import com.movie.movieonline.repository.CommentRepository;
import com.movie.movieonline.repository.MovieRepository;
import com.movie.movieonline.repository.WishListRepository;
import com.movie.movieonline.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private CommentRepository commentRepository;
    private WishListRepository wishListRepository;

    @Autowired
    MovieServiceImpl(
        MovieRepository movieRepository, 
        CommentRepository commentRepository, 
        WishListRepository wishListRepository){
        this.movieRepository = movieRepository;
        this.commentRepository = commentRepository;
        this.wishListRepository = wishListRepository;
    }

    @Override
    public Movie addMovie(Movie movie) {
        if(movieRepository.existsById(movie.getId())){
            return null;
        }
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> 
                                new ResourceNotFoundException("Book", "Id", movieId));
        for(Comment cmt:movie.getComments()){
            commentRepository.delete(cmt);
        }
        for(WishList wish:movie.getWishLists()){
            wishListRepository.delete(wish);
        }
        movieRepository.delete(movie);
        
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie updateMovie(Movie movie, Long movieId) {
        return null;
    }

    @Override
    public Movie getMovieById(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> 
            new ResourceNotFoundException("Book", "Id", movieId));
    }

}
