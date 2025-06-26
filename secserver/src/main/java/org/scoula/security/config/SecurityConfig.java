package org.scoula.security.config;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity // Spring Security 활성화
@Log4j2
@MapperScan(basePackages = {"org.scoula.security.account.mapper"})
@ComponentScan(basePackages = "org.scoula.security")
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // DB 기반 인증 처리를 위한 서비스 (in-memory 방식과 병행 불가)
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 보안 강화를 위한 BCrypt 해시 알고리즘 사용
        return new BCryptPasswordEncoder();
    }
    // 문자셋 필터 정의
    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");           // 문자 인코딩 설정
        encodingFilter.setForceEncoding(true);         // 강제 인코딩 적용
        return encodingFilter;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CSRF 필터보다 먼저 문자셋 필터 적용
        http.addFilterBefore(encodingFilter(), CsrfFilter.class);
//        // 경로별 접근 권한 설정
        http.authorizeRequests()
                .antMatchers("/security/all").permitAll() // 모든 사용자 접근 허용
                .antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')") // ADMIN 권한 보유자만 허용
                .antMatchers("/security/member").access("hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')"); // MEMBER, ADMIN 권한 보유자 허용
        http.formLogin() // form 기반 로그인 설정 시작
                .loginPage("/security/login") // 커스텀 로그인 페이지 (GET 요청)
                .loginProcessingUrl("/security/login") // 로그인 요청 처리 경로 (POST 요청)
                .defaultSuccessUrl("/"); // 로그인 성공 시 리다이렉트될 기본 URL 설정
        http.logout()                               // 로그아웃 설정 시작
                .logoutUrl("/security/logout")          // POST: 로그아웃 호출 url
                .invalidateHttpSession(true)            // 세션 invalidate
                .deleteCookies("remember-me", "JSESSION-ID") // 삭제할 쿠키 목록
                .logoutSuccessUrl("/security/logout");  // GET: 로그아웃 이후 이동할 페이지
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        // DB에서 사용자 정보를 조회하고, 해시된 비밀번호로 인증
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}