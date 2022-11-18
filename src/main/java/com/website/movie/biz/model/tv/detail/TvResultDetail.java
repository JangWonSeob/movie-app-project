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

    private boolean adult;                                          // 성인여부-v
    private String backdropPath;                                    // 뒷배경_이미지-v
    private int[] episodeRunTime;                                   // 시리즈_런타임
    private Date firstAirDate;                                      // 첫_방송일-v
    private List<TvGenres> genres;                                  // 장르-v
    private String homepage;                                        // 홈페이지 주소
    private String id;                                              // ID-v
    private boolean inProduction;                                   // 현재_상영_여부
    private Date lastAirDate;                                       // 마지막_방송일-v
    private TvLastEpisodeToAir lastEpisodeToAir;                    // 마지막회_방송
    private String name;                                            // 제목-v
    private int numberOfEpisodes;                                   // 에피소드_수
    private int numberOfSeasons;                                    // 시즌_수
    private String[] originCountry;                                 // 원산지
    private String originalLanguage;                                // 원_언어-v
    private String originalName;                                    // 원_제목-v
    private String overview;                                        // 내용-v
    private double popularity;                                      // 인기도-v
    private String posterPath;                                      // 포스트 이미지-v
    private List<TvSeasons> seasons;                                // 시즌_목록
    private String status;                                          // 상태
    private double voteAverage;                                     // 평점-v
    private int voteCount;                                          // 평점 잠여 인원-v
    private TvVideoResult videos;                                   // 비디오
    @JsonProperty("watch/providers")
    private TvWatchProvidersResult watchProviders;                  // 지원사

}
