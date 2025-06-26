package org.scoula.security.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class, SecurityConfig.class }) // 설정 클래스 주입
@Log4j2
class JwtProcessorTest {

    @Autowired
    JwtProcessor jwtProcessor;

    // 토큰 생성 테스트
    @Test
    void generateToken() {
        String username = "user0";
        String token = jwtProcessor.generateToken(username);
        log.info(token); // → 토큰 복사해서 아래 테스트에 붙여넣기 가능
        assertNotNull(token);
    }

    // 토큰에서 사용자명 추출 테스트
    @Test
    void getUsername() {
        // 위 테스트에서 복사한 토큰 입력
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTc1MDc0NTgwMiwiZXhwIjoxNzUwNzQ2MTAyfQ.LWOkKuNwumCGqwIJSPJk4jdfwJ1ZW1jetGHB2EWWakQxolsn2UUMTuhuJOGxcylS";

        String username = jwtProcessor.getUsername(token);
        log.info(username);
        assertNotNull(username); // "user0"이 나와야 성공
    }

    // 토큰 유효성 검사 테스트
    @Test
    void validateToken() {
        // 발급 5분 이내에만 true, 그 이후엔 예외 발생
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTc1MDcyODk3NiwiZXhwIjoxNzUwNzI5Mjc2fQ.wugkQ2Iqnj9xf_DNyAY-6goOXt8OeU8QOSzshNKWmJ3GwTJ6uno9n4gSRzCi7iBP";

        boolean isValid = jwtProcessor.validateToken(token);
        log.info(isValid); // true
        assertTrue(isValid); // 5분 지나면 예외 발생함
    }
}