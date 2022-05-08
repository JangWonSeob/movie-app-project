package com.website.movie.biz.model.ouput;

import com.website.movie.biz.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardOutputModel {

    private int id;
    private String category;
    private String title;
    private String contents;
    private int viewCount;
    private boolean displayYn;

    private Date regDt;
    private int regId;
    private Date udtDT;

    public static BoardOutputModel toModel(BoardDto parameter) {

        return BoardOutputModel.builder()
                .id(parameter.getId())
                .category(parameter.getCategory())
                .title(parameter.getTitle())
                .contents(parameter.getContents())
                .viewCount(parameter.getViewCount())
                .displayYn(parameter.isDisplayYn())
                .regDt(parameter.getRegDt())
                .regId(parameter.getRegId())
                .udtDT(parameter.getUdtDT())
                .build();
    }

}
