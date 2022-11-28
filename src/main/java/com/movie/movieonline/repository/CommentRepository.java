package com.movie.movieonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.movieonline.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}
