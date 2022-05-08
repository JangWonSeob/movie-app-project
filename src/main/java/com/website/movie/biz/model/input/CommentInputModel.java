package com.website.movie.biz.model.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentInputModel extends InputBaseDto {

    private int id;
    private int boardId;
    private int commentId;
    private String contents;
    private boolean displayYn;

}
