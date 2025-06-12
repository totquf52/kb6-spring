package org.scoula.ex02.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    // 루트 어플리케이션 설정 (서비스, DAO, DB 등)
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    // 서블릿 어플리케이션 설정 (Controller, ViewResolver 등)
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ServletConfig.class};
    }

    // DispatcherServlet의 URL 매핑
    @Override
    protected String[] getServletMappings() {
        // 모든 요청을 DispatcherServlet이 처리
        return new String[]{"/"};
    }

    // 한글 POST 요청 인코딩 필터 설정
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return new Filter[]{characterEncodingFilter};
    }
}
