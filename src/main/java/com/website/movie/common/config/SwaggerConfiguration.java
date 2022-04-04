package com.website.movie.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.website.movie"))
                .paths(PathSelectors.any())                               // 모든 경로
//                .paths(PathSelectors.ant("api/**"))                     // url/api 하위 경로
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("영화 커뮤니티")
                .version("1.0.0")
                .description("영화 커뮤티니 사이트 swagger api 입니다.")
                .build();
    }

}

