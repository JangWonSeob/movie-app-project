package com.website.movie.biz.model.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardSearchModel {


    private int id;             // selectOne 필수 값
    private String category;    // selectOne, selectList 필수 값

    private String searchType;
    private String searchValue;

    private String sqlSelectType;

    private int pageIndex;
    private int pageSize;

    private int startIndex;

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
