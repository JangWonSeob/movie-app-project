package com.website.movie.biz.dto;

import com.website.movie.biz.model.input.BoardInputModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto extends BaseDto {

    private int id;
    private String category;
    private String title;
    private String contents;
    private int viewCount;
    private boolean displayYn;

    public static BoardDto toDto(BoardInputModel model) {

        return BoardDto.builder()
                .id(model.getId())
                .category(model.getCategory())
                .title(model.getTitle())
                .contents(model.getContents())
                .displayYn(model.isDisplayYn())
                .build();
    }
}
