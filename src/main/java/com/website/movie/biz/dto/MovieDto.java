package com.website.movie.biz.dto;

import com.website.movie.biz.model.movie.MovieData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto extends BaseDto {

    private String id;
    private String title;
    private String overview;
    private boolean adult;
    private String originalLanguage;
    private String originalTitle;
    private String backdropPath;
    private String postPath;
    private Date releaseDate;
    private double voteAverage;
    private int voteCount;
    private double popularity;
    private int viewCount;
    private boolean displayYn;

    public static MovieDto toDto(MovieData module) {
        return MovieDto.builder()
                .id(module.getId())
                .title(module.getTitle())
                .overview(module.getOverview())
                .adult(module.isAdult())
                .originalLanguage(module.getOriginalLanguage())
                .originalTitle(module.getOriginalTitle())
                .backdropPath(module.getBackdropPath())
                .postPath(module.getPostPath())
                .releaseDate(module.getReleaseDate())
                .voteAverage(module.getVoteAverage())
                .voteCount(module.getVoteCount())
                .popularity(module.getPopularity())
                .build();
    }

}
