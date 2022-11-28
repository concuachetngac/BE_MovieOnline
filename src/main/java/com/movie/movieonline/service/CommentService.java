package com.movie.movieonline.service;

import com.movie.movieonline.domain.Comment;

public interface CommentService {
    void addComment(Comment cmt);
    void deleteComment(Long cmtId);
}
