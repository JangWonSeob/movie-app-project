package com.website.movie.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto extends BaseDto {

    private int id;
    private int userId;
    private int boardId;
    private int commentId;
    private String contents;

}
