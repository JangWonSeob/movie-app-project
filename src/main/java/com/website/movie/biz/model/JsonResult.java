package com.website.movie.biz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {

    private JsonHeader header;
    private JsonBody body;

    public static JsonResult success() {
        return success("");
    }

    public static JsonResult success(final Object data) {
        final JsonHeader header = new JsonHeader(true, "");
        final JsonBody body = new JsonBody(data);
        return new JsonResult(header, body);
    }

    public static JsonResult fail(final String message) {
        final JsonHeader header = new JsonHeader(false, message);
        final JsonBody body = new JsonBody("");
        return new JsonResult(header, body);
    }
}
