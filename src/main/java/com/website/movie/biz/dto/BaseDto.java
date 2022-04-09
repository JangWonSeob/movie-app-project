package com.website.movie.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDto {

    protected LocalDateTime regDt;
    protected String regId;
    protected LocalDateTime udtDT;
    protected String udtId;
    protected LocalDateTime delDt;
    protected String delId;
    protected boolean delYn;
}
