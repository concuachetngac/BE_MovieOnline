package com.movie.movieonline.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;


@Entity
@Table(name="wish_lists")
@Getter @Setter @NoArgsConstructor
public class WishList {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @JsonIgnore
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    User user;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name="movie_id", referencedColumnName = "id")
    Movie movie;

    public WishList(User user, Movie movie){
        super();
        this.movie=movie;
        this.user=user;
    }
}
