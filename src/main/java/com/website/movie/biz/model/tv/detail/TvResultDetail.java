package com.website.movie.biz.model.tv.detail;

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
public class TvResultDetail {

    private boolean adult;                                          // 성인여부
    private String backdropPath;                                    // 뒷배경 이미지
    private int[] episodeRunTime;                                   // 시리즈_런타임
    private Date firstAirDate;                                      // 첫_방송일
    private List<TvGenres> genres;                                  // 장르
    private String homepage;                                        // 홈페이지 주소
    private String id;                                              // ID
    private boolean inProduction;                                   // 현재_상영_여부
    private Date lastAirDate;                                       // 마지막_방송일
    private String[] originCountry;                                 // 원산지
    private String originalLanguage;                                // 원_언어
    private String originalTitle;                                   // 원_제목
    private String overview;
    private double popularity;                                      // 인기도
    private String posterPath;                                      // 포스트 이미지
//    private String productionCompanies;
//    private String productionCountries;
//    private String spokenLanguages;
    private String status;                                          // 상태
    private String tagline;                                         // ?
    private String type;                                            // 타입
    private double voteAverage;                                     // 평점
    private int voteCount;                                          // 평점 잠여 인원
//    private String images;
    private TvVideoResult videos;                                   // 비디오
    @JsonProperty("watch/providers")
    private TvWatchProvidersResult watchProviders;                  // 지원사

    private TvCredits credits;


}
