package com.website.movie.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BookMarkDto extends BaseDto {

    public final static String TABLE_NAME_BOARD = "board";
    public final static String TABLE_NAME_MOVIE = "movie";
    public final static String TABLE_NAME_TV = "tv";

    private int id;
    private int userId;
    private String tableId;
    private String tableName;

}
