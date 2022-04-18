package com.website.movie.biz.model.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardInputModel extends InputBaseDto {

    private int id;
    private String category;
    private String title;
    private String contents;

}
