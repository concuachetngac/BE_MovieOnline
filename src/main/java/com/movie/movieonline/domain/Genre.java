package com.movie.movieonline.domain;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import lombok.*;

@Entity
@Table(name="genres")
@Getter @Setter @NoArgsConstructor
public class Genre {
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany(mappedBy = "genres")
    private List<Movie> movies;

    public Genre(Long id, String name){
        this.id=id;
        this.name=name;
    }
}
