package com.website.movie.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeDto extends BaseDto {

    private int id;
    private String subId;
    private String type;
    private String description;
    private String name;
    private int sortValue;
    private String imgPath;

    private List<MovieDto> movieList;

    public String getMovieTitle() {
        String result = "";

        if(!StringUtils.isEmpty(description)) {
            result = " # " + description;

        }
        return result;
    }
}
