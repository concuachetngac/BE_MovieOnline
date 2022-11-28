package com.movie.movieonline.domain;

import java.sql.Timestamp;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Table(name="comments")
@Getter @Setter @NoArgsConstructor
public class Comment {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    
    @Column(name="comment_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    Timestamp time;

    @Column(name="content")
    String content;

    @JsonIgnore
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name="movie_id", referencedColumnName = "id")
    Movie movie;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    User user;

    public Comment(String content, Timestamp time, User user, Movie movie){
        this.content=content;
        this.time=time;
        this.user=user;
        this.movie=movie;
    }
}
