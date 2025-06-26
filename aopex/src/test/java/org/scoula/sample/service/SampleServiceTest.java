package org.scoula.sample.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class) // JUnit5와 Spring 통합
@ContextConfiguration(classes = { RootConfig.class }) // 설정 클래스 지정
class SampleServiceTest {

    @Autowired
    private SampleService service;

    @Test
    public void doAdd() throws Exception {
        log.info(service.doAdd("123", "456")); // 실제 서비스 호출 + 결과 로그 출력
    }

    @Test
    public void addError() throws Exception {
        // "ABC"는 정수로 변환할 수 없어 NumberFormatException 발생
        log.info(service.doAdd("123", "ABC"));
    }
}