package com.website.movie.biz.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class InputBaseDto {

    protected int loginUserId;

    protected Date regDt;
    protected String regId;
    protected Date updDT;
    protected String updId;
    protected Date delDt;
    protected String delId;
    protected boolean delYn;

}
