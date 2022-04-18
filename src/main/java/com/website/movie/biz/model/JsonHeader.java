package com.website.movie.biz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonHeader {

    private boolean result;
    private String message;

//    public JsonHeader(boolean result, String message) {
//        this.result = result;
//        this.message = message;
//    }
}
