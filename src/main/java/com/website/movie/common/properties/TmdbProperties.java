package com.website.movie.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("tmdb")
public class TmdbProperties {

    private String apiKey;
    private String baseUrl;
    private String imgBaseUrl;

}
