package com.movie.movieonline.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.*;

@Entity
@Table(name="movies")
@Getter @Setter @NoArgsConstructor
@JsonIdentityInfo(
   generator = ObjectIdGenerators.PropertyGenerator.class,
   property = "id")
public class Movie {
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="vote_average")
    private int vote_average;

    @Column(name="is_movie")
    private int isMovie;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="release_date")
    private Date release_date;

    @Column(name="poster_path")
    private String poster_path;

    @Column(name="backdrop_path")
    private String backdrop_path;

    @Column(name="overview")
    private String overview;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Long> genre_ids;

    @ManyToMany
    @JsonIdentityReference(alwaysAsId = true)
    @JoinTable(name="movies_genres", 
                joinColumns = @JoinColumn(name="movie_id"),
                inverseJoinColumns = @JoinColumn(name="genre_id"))
    private List<Genre> genres;

    @JsonIgnore
    @OneToMany(mappedBy = "movie", targetEntity = WishList.class, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<WishList> wishLists;

    @OneToMany(mappedBy = "movie", targetEntity = Comment.class, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments;

    public Movie(Long id, String title, int voteAvg, Date releaseDate){
        this.id = id;
        this.title = title;
        this.vote_average = voteAvg;
        this.release_date = releaseDate;
    }
}
