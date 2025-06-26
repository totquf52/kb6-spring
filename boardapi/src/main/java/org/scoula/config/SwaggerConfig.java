package org.scoula.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableSwagger2 // Swagger2 활성화
public class SwaggerConfig {

    private final String API_NAME = "Board API";
    private final String API_VERSION = "1.0";
    private final String API_DESCRIPTION = "Board API 명세서";

    // 문서 제목, 설명, 버전 등을 설정
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(API_NAME)
                .description(API_DESCRIPTION)
                .version(API_VERSION)
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // @RestController가 붙은 클래스만 문서화 대상이 됨
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                // 모든 URL 경로 포함
                .paths(PathSelectors.any())
                .build()
                // 위에서 정의한 API 정보 반영
                .apiInfo(apiInfo());
    }
}