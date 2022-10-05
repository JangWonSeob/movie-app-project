package com.website.movie.biz.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.website.movie.biz.model.movie.MovieData;
import com.website.movie.biz.model.movie.detail.MovieResultDetail;
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

    private String id;                      // PK
    private String title;                   // 재목
    private String overview;                // 내용
    private String genreIds;                // 장르 ID 리스트
    private String genreNames;              // 장르 이름 리스트
    private boolean adult;                  // 성인 여부
    private String originalLanguage;        // 원래 언어
    private String originalTitle;           // 원래 제목
    private String backdropPath;            // 배경 이미지
    private String posterPath;              // 포스트 이미지
    private Date releaseDate;               // 개봉일
    private int runtime;                    // 영화 시간
    private double voteAverage;             // 평점
    private int voteCount;                  // 평점 참여자
    private double popularity;              // 평가 지표
    private int budget;                     // 예산
    private long revenue;                   // 수익
    private int viewCount;                  // 조회수
    private boolean displayYn;              // 노출 여부

    // join
    private List<MovieGenreDto> genreList;
    private List<MovieWatchProvidersDto> watchProvidersBuyList;
    private List<MovieWatchProvidersDto> watchProvidersRentList;
    private List<MovieYoutubeDto> watchYoutubeList;

    private boolean bookMarkYn;             // 즐겨찾기 여부

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

    public static MovieDto toDto(MovieResultDetail module) {
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
                .runtime(module.getRuntime())
                .voteAverage(module.getVoteAverage())
                .voteCount(module.getVoteCount())
                .popularity(module.getPopularity())
                .budget(module.getBudget())
                .revenue(module.getRevenue())
                .build();
    }
}
