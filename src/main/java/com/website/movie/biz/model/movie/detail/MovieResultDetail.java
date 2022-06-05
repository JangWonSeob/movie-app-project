package com.website.movie.biz.model.movie.detail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MovieResultDetail {

    private boolean adult;                                          // 성인여부
    private String backdropPath;                                    // 뒷배경 이미지
    private int budget;                                             // 예산
    private List<MovieGenres> genres;                               // 장르
    private String homepage;                                        // 홈페이지 주소
    private String id;                                              // ID
    private String imdb_id;                                         // IMDB_ID
    private String originalLanguage;                                // 원_언어
    private String originalTitle;                                   // 원_제목
    private String overview;
    private double popularity;                                      // 인기도
    private String posterPath;                                      // 포스트 이미지
//    private String productionCompanies;
//    private String productionCountries;
    private Date releaseDate;                                       // 개봉일
    private long revenue;                                           // 수익
    private int runtime;                                            // 영화 시간
//    private String spokenLanguages;
    private String status;                                          // 상태
    private String tagline;                                         // ?
    private String title;                                           // 제목
    private double voteAverage;                                     // 평점
    private int voteCount;                                          // 평점 잠여 인원
//    private String images;
    private MovieVideoResult videos;                                // 비디오
    @JsonProperty("watch/providers")
    private MovieWatchProvidersResult watchProviders;               // 지원사

    private MovieCredits credits;


}
