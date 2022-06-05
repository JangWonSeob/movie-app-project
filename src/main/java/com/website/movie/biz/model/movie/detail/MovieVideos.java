package com.website.movie.biz.model.movie.detail;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class MovieVideos {

    @JsonProperty("iso_639_1")
    private String iso6391;
    @JsonProperty("iso_3166_1")
    private String iso31661;
    private String name;
    private String key;
    private String site;
    private int size;
    private String type;
    private boolean official;
    private String publishedAt;
    private String id;

}
