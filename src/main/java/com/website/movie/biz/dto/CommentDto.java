package com.website.movie.biz.dto;

import com.website.movie.biz.model.input.CommentInputModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto extends BaseDto {

    private int id;
    private int userId;
    private int boardId;
    private int commentId;
    private String contents;


    public static CommentDto toDto(CommentInputModel model) {

        return CommentDto.builder()
                .id(model.getId())
                .boardId(model.getBoardId())
                .commentId(model.getCommentId())
                .contents(model.getContents())
                .loginUserId(model.getLoginUserId())
                .build();
    }
}
