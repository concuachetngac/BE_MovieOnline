package com.movie.movieonline.util;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Getter @Setter @NoArgsConstructor
public class CommentRequest {
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp time;
}
