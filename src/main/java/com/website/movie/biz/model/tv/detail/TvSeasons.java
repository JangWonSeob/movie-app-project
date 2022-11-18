package com.website.movie.biz.model.tv.detail;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TvSeasons {

    private String airDate;
    private int episodeNumber;
    private int id;
    private String name;
    private String overview;
    private String productionCode;
    private String runtime;
    private int seasonNumber;
    private int showId;
    private String stillPath;
    private int voteAverage;
    private int voteCount;

}