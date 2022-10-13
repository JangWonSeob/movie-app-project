package com.website.movie.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.text.SimpleDateFormat;
import java.util.Locale;

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

    private String regNickname;

    public String getRegDtText() {
        String result = "";

        if (regDt != null) {
            SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
            result = simpleDateFormat.format(regDt);
        }
        return result;
    }

}
