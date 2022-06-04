package com.website.movie.biz.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.website.movie.biz.model.movie.MovieData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto extends BaseDto {

    public static final String CODE_TYPE = "MOVIE_GENRE";

    @JsonIgnore
    private final String IMG_BASE_URL = "https://image.tmdb.org/t/p/w300";

    private String id;
    private String title;
    private String overview;
    private String genreIds;
    private String genreNames;
    private boolean adult;
    private String originalLanguage;
    private String originalTitle;
    private String backdropPath;
    private String posterPath;
    private Date releaseDate;
    private double voteAverage;
    private int voteCount;
    private double popularity;
    private int viewCount;
    private boolean displayYn;

    private List<MovieGenreDto> genreList;

    // search
    private String searchGenre;

    public String getFullBackdropPath() {
        String result = "";

        if(!StringUtils.isEmpty(backdropPath)) {
            result = IMG_BASE_URL + backdropPath;
        }
        return result;
    }

    public String getFullPosterPath() {
        String result = "";

        if(!StringUtils.isEmpty(posterPath)) {
            result = IMG_BASE_URL + posterPath;
        }
        return result;
    }

    public String[] getGenreName(){
        if (!StringUtils.isEmpty(genreNames)) {
            return genreNames.split(",");
        }

        return new String[0];
    }

    public static MovieDto toDto(MovieData module) {
        return MovieDto.builder()
                .id(module.getId())
                .title(module.getTitle())
                .overview(module.getOverview())
                .adult(module.isAdult())
                .originalLanguage(module.getOriginalLanguage())
                .originalTitle(module.getOriginalTitle())
                .backdropPath(module.getBackdropPath())
                .posterPath(module.getPosterPath())
                .releaseDate(module.getReleaseDate())
                .voteAverage(module.getVoteAverage())
                .voteCount(module.getVoteCount())
                .popularity(module.getPopularity())
                .build();
    }

}
