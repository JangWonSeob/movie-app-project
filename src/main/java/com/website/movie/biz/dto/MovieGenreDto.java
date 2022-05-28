package com.website.movie.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MovieGenreDto extends BaseDto {

    private String id;
    private String movieId;
    private String genreId;

    // join
    private String genreName;

}
