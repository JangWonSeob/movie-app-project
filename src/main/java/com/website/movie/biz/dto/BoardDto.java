package com.website.movie.biz.dto;

import com.website.movie.biz.model.input.BoardInputModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto extends BaseDto {

    private int id;
    private String category;
    private String title;
    private String contents;
    private int viewCount;
    private boolean displayYn;

    private String writer;

    private boolean myLike;
    private boolean myUnlike;

    private int likeTotalCount;
    private int unlikeTotalCount;

    private boolean myBookMark;

    // 프론트 넘버링
    private int boardNo;

    // search
    private String searchCategory;      // 카테고리 검색

    // join
//    List<CommentDto> comments;
//    int commentTotalCount;

    public static BoardDto toDto(BoardInputModel model) {

        return BoardDto.builder()
                .id(model.getId())
                .category(model.getCategory())
                .title(model.getTitle())
                .contents(model.getContents())
                .displayYn(model.isDisplayYn())
                .loginUserId(model.getLoginUserId())
                .build();
    }
}
