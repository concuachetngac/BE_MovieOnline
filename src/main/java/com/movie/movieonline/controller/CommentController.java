package com.movie.movieonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.movieonline.domain.Comment;
import com.movie.movieonline.domain.Movie;
import com.movie.movieonline.domain.User;
import com.movie.movieonline.service.CommentService;
import com.movie.movieonline.service.MovieService;
import com.movie.movieonline.service.UserService;
import com.movie.movieonline.service.impl.UserDetailsImpl;
import com.movie.movieonline.util.CommentRequest;
import com.movie.movieonline.util.MessageResponse;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private UserService userService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private CommentService commentService;
    
    @PreAuthorize("hasRole('USER')")
    @PostMapping("add/{movieId}")
    public ResponseEntity<Comment> addComment(@PathVariable long movieId, @RequestBody CommentRequest commentRequest){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.loadUserByUsername(userDetails.getUsername());
        Movie movie = movieService.getMovieById(movieId);

        Comment comment = new Comment(commentRequest.getContent(), commentRequest.getTime(), user, movie);
        commentService.addComment(comment);

        return new ResponseEntity<Comment> (comment, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable long id){
        commentService.deleteComment(id);
        return new ResponseEntity<MessageResponse> (new MessageResponse("Delete Successful"), HttpStatus.OK);
    }
}
