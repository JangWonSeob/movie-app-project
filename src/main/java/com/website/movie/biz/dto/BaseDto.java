package com.website.movie.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDto {

    protected Date regDt;
    protected String regId;
    protected Date udtDT;
    protected String udtId;
    protected Date delDt;
    protected String delId;
    protected boolean delYn;
}
