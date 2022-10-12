package com.website.movie.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto extends BaseDto {

    public static final String TABLE_NAME_BOARD = "board";
    public static final String TABLE_NAME_MOVIE = "movie";

    private int id;
    private int userId;
    private String tableName;
    private int tableId;
    private int commentId;
    private String contents;

    private int childCommentCount;

}
