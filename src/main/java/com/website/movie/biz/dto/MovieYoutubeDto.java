package com.website.movie.biz.dto;

import com.website.movie.biz.model.movie.detail.MovieVideos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.thymeleaf.util.StringUtils;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MovieYoutubeDto extends BaseDto {

    private String id;                  // PK
    private String movieId;             // 영화 TALBE ID
    private String iso6391;             // iso6391
    private String iso31661;            // iso31661
    private String title;               // 유튜트_제목
    private String youtubeKey;          // 유튜브_키
    private int size;                   // 사이즈
    private String youtubeId;           // 유튜브_ID
    private int sortValue;              // 정렬 순서

    public String youtubeUrl() {
        String result = "";

        if (!StringUtils.isEmpty(youtubeKey)) {
            result = "https://www.youtube.com/embed/" + youtubeKey;
        }
        return result;
    }


    public static MovieYoutubeDto toDto(MovieVideos model, String movieId, int sortValue) {
        return MovieYoutubeDto.builder()
                .movieId(movieId)
                .iso6391(model.getIso6391())
                .iso31661(model.getIso31661())
                .title(model.getName())
                .youtubeKey(model.getKey())
                .size(model.getSize())
                .youtubeId(model.getId())
                .sortValue(sortValue)
                .build();
    }
}

