package com.website.movie.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDto {

    private int loginUserId;

    protected Date regDt;
    protected int regId;
    protected Date udtDT;
    protected int udtId;
    protected Date delDt;
    protected int delId;
    protected boolean delYn;

    protected String sqlInsertType;
    protected String sqlUpdateType;
    protected String sqlSelectType;
    protected String sqlDeleteType;

    protected String searchType;
    protected String searchValue;

    protected int pageIndex;
    protected int pageSize;

    protected int startIndex;

    public void initPage() {

        if (this.pageIndex < 1) {
            this.pageIndex = 1;
        }
        if (this.pageSize < 1) {
            this.pageSize = 10;
        }

        this.startIndex = (this.pageIndex - 1) * this.pageSize;
    }

}
