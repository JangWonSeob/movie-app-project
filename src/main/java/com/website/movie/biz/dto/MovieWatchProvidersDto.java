package com.website.movie.biz.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.website.movie.biz.model.movie.detail.MovieKrBuyRent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.thymeleaf.util.StringUtils;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MovieWatchProvidersDto extends BaseDto {

    @JsonIgnore
    private final String IMG_BASE_URL = "https://www.themoviedb.org/t/p/original";

    public static final String TYPE_BUY = "buy";
    public static final String TYPE_RENT = "rent";

    private String id;                  // PK
    private String movieId;             // 영화 TALBE ID
    private int providerId;             // 공급자 ID
    private String providerName;        // 공급자 이름
    private int displayPriority;        // 표시 순위
    private String logoPath;            // 로그 위치
    private String type;                // 타입

    private String searchProviderType;

    public String getFullLogPath() {
        String result = "";

        if (!StringUtils.isEmpty(logoPath)) {
            result = IMG_BASE_URL + logoPath;
        }
        return result;
    }

    public static MovieWatchProvidersDto toDto(String movieId, MovieKrBuyRent model, String type) {
        return MovieWatchProvidersDto.builder()
                .movieId(movieId)
                .providerId(model.getProviderId())
                .providerName(model.getProviderName())
                .displayPriority(model.getDisplayPriority())
                .logoPath(model.getLogoPath())
                .type(type)
                .build();
    }
}
