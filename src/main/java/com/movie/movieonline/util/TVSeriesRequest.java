package com.movie.movieonline.util;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter @Setter @NoArgsConstructor
public class TVSeriesRequest {

    private Long id;

    private String name;

    private int vote_average;

    private int isMovie;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date first_air_date;

    private String poster_path;

    private String backdrop_path;

    private String overview;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Long> genre_ids;

    
}
