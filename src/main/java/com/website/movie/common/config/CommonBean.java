package com.website.movie.common.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Configuration
public class CommonBean {

    @Bean
    public RestTemplate restTemplate() {
        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

        factory.setReadTimeout(5000);
        factory.setConnectTimeout(3000);

        final HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(50)        // 최대 커넥션 수
                .setMaxConnPerRoute(20)     // 각 호스트 (IP와 Port의 조합당 커넥션 풀에 생성 가능한 커넥션 수)
                .build();

        factory.setHttpClient(httpClient);

        final RestTemplate restTemplate = new RestTemplate(factory);

        // 한글 포맷
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        return restTemplate;
    }

}
