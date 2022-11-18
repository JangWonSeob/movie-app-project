package com.website.movie.biz.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.website.movie.biz.model.movie.MovieData;
import com.website.movie.biz.model.movie.detail.MovieResultDetail;
import com.website.movie.biz.model.tv.detail.TvResultDetail;
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
public class TvDto extends BaseDto {

    public static final String CODE_TYPE_TV_GENRE = "TV_GENRE";

    @JsonIgnore
    private final String IMG_BASE_URL = "https://image.tmdb.org/t/p/w300";

    private String id;                      // PK
    private String title;                   // 제목
    private String overview;                // 내용
    private String genreIds;                // 장르 ID 리스트
    private String genreNames;              // 장르 이름 리스트
    private boolean adult;                  // 성인 여부
    private String originalLanguage;        // 원래 언어
    private String originalTitle;           // 원래 제목
    private String backdropPath;            // 배경 이미지
    private String posterPath;              // 포스트 이미지
    private Date firstAirDate;              // 첫_방송일
    private Date lastAirDate;               // 마지막_방송일
    private double voteAverage;             // 평점
    private int voteCount;                  // 평점 참여자
    private double popularity;              // 평가 지표
    private int seasonsNumber;              // 시리즈_개수
    private int viewCount;                  // 조회수
    private boolean displayYn;              // 노출 여부


    // join
    private List<TvWatchProvidersDto> watchProvidersList;
    private List<TvYoutubeDto> watchYoutubeList;

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


    public static TvDto toDto(TvResultDetail module) {
        return TvDto.builder()
                .id(module.getId())
                .title(module.getName())
                .overview(module.getOverview())
                .adult(module.isAdult())
                .originalLanguage(module.getOriginalLanguage())
                .originalTitle(module.getOriginalName())
                .backdropPath(module.getBackdropPath())
                .posterPath(module.getPosterPath())
                .firstAirDate(module.getFirstAirDate())
                .lastAirDate(module.getLastAirDate())
                .voteAverage(module.getVoteAverage())
                .voteCount(module.getVoteCount())
                .popularity(module.getPopularity())
                .seasonsNumber(module.getNumberOfSeasons())
                .build();
    }
}
