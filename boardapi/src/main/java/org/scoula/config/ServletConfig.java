package org.scoula.config;

import io.swagger.models.Swagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.swing.text.html.HTML;

@EnableWebMvc // Spring MVC 기능 활성화
@ComponentScan(basePackages = {
        "org.scoula.exception",
        "org.scoula.controller",
        "org.scoula.board.controller",
        "org.scoula.weather.controller"
})
public class ServletConfig implements WebMvcConfigurer {

    // 정적 자원 처리 설정 (CSS, JS, 이미지 등)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**") // 요청 URL 경로
                .addResourceLocations("/resources/"); // 실제 리소스 위치
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("/resources/favicon.ico");
        // Swagger UI HTML 파일 매핑
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        // Swagger 관련 정적 자원 (CSS, JS 등) 매핑
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        // Swagger 리소스 관련 핸들러 (문서 JSON 등)
        registry.addResourceHandler("/swagger-resources/**")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/v2/api-docs")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

    // JSP ViewResolver 설정
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        
        bean.setViewClass(JstlView.class); // JSTL 지원 뷰
        bean.setPrefix("/WEB-INF/views/"); 
        bean.setSuffix(".jsp");
        
        registry.viewResolver(bean); // 등록
    }

    // Servlet 3.0 파일 업로드 사용 시 - MultipartResolver 빈 등록
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
