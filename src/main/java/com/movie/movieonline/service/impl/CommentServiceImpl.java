package com.movie.movieonline.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.movieonline.domain.Comment;
import com.movie.movieonline.exception.ResourceNotFoundException;
import com.movie.movieonline.repository.CommentRepository;
import com.movie.movieonline.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void addComment(Comment cmt) {
        commentRepository.save(cmt);
        
    }

    @Override
    public void deleteComment(Long cmtId) {
        commentRepository.findById(cmtId).orElseThrow(() -> 
                                new ResourceNotFoundException("Comment", "Id", cmtId));
        commentRepository.deleteById(cmtId);
        
    }
    
}
