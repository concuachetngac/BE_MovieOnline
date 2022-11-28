package com.movie.movieonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.movieonline.domain.Movie;
import com.movie.movieonline.domain.User;
import com.movie.movieonline.domain.WishList;
import com.movie.movieonline.service.MovieService;
import com.movie.movieonline.service.UserService;
import com.movie.movieonline.service.WishListService;
import com.movie.movieonline.service.impl.UserDetailsImpl;
import com.movie.movieonline.util.MessageResponse;

@RestController
@RequestMapping("/api/wishlists")
public class WishListController {
    @Autowired
    private WishListService wishListService;
    @Autowired
    private UserService userService;
    @Autowired
    private MovieService movieService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("add/{movieId}")
    public ResponseEntity<WishList> addToWishList(@PathVariable long movieId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.loadUserByUsername(userDetails.getUsername());
        Movie movie = movieService.getMovieById(movieId);

        WishList wishlist = new WishList(user, movie);
        wishListService.addToWishList(wishlist);

        return new ResponseEntity<WishList> (wishlist, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteFromWishList(@PathVariable long id){
        wishListService.deleteFromWishList(id);
        return new ResponseEntity<MessageResponse> (new MessageResponse("Delete Successful"), HttpStatus.OK);
    }
}
