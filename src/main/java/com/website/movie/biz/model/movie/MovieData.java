package com.website.movie.biz.model.movie;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MovieData {

    private String id;
    private String title;
    private String overview;
    private String[] genreIds;
    private boolean adult;
    private String originalLanguage;
    private String originalTitle;
    private String backdropPath;
    private String posterPath;
    private Date releaseDate;
    private double voteAverage;
    private int voteCount;
    private double popularity;

}
