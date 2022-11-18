package com.website.movie.biz.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.website.movie.biz.model.tv.detail.TvKr;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.thymeleaf.util.StringUtils;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TvWatchProvidersDto extends BaseDto {

    @JsonIgnore
    private final String IMG_BASE_URL = "https://www.themoviedb.org/t/p/original";


    private String id;                  // PK
    private String tvId;                // TV_TABLE_ID
    private int providerId;             // 공급자 ID
    private String providerName;        // 공급자 이름
    private int displayPriority;        // 표시 순위
    private String logoPath;            // 로그 위치

    public String getFullLogPath() {
        String result = "";

        if (!StringUtils.isEmpty(logoPath)) {
            result = IMG_BASE_URL + logoPath;
        }
        return result;
    }

    public static TvWatchProvidersDto toDto(String tvId, TvKr model) {
        return TvWatchProvidersDto.builder()
                .tvId(tvId)
                .providerId(model.getProviderId())
                .providerName(model.getProviderName())
                .displayPriority(model.getDisplayPriority())
                .logoPath(model.getLogoPath())
                .build();
    }
}
