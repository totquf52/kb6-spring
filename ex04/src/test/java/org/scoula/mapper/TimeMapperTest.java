package org.scoula.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) // JUnit5에서 Spring 기능 사용
@ContextConfiguration(classes = { RootConfig.class }) // 스프링 설정 클래스 지정
@Log4j2
public class TimeMapperTest {

    @Autowired
    private TimeMapper timeMapper; // TimeMapper 인터페이스를 스프링이 자동 주입

    @Test
    @DisplayName("TimeMapper의 getTime()")
    public void getTime() {
        // 프록시로 생성된 구현 클래스 이름 출력
        log.info(timeMapper.getClass().getName());

        // 실제 DB에서 현재 시간 가져오기
        log.info(timeMapper.getTime());
    }


    @Test
    @DisplayName("TimeMapper의 getTime2()")
    public void getTime2() {
        log.info("getTime2");                      // 로그로 메서드 진입 확인
        log.info(timeMapper.getTime2());           // 실제 결과 출력
    }
}