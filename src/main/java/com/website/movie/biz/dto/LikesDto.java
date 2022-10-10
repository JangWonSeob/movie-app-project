package com.website.movie.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LikesDto extends BaseDto {

    private int id;
    private int userId;
    private int boardId;

    // 민수
    private boolean likeYn;             // 좋아요 여부


}
