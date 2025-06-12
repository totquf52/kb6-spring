package org.scoula.config;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * RootConfig 설정을 기반으로 DataSource가 정상적으로 작동하는지 테스트
 */
@ExtendWith(SpringExtension.class) // JUnit5와 스프링 통합
@ContextConfiguration(classes = {RootConfig.class}) // 테스트 설정으로 RootConfig 사용
@Log4j2
class RootConfigTest {

    @Autowired
    private DataSource dataSource; // RootConfig에서 등록한 DataSource 주입

    @Test
    @DisplayName("DataSource 연결이 된다.")
    public void dataSource() throws SQLException {
        // 커넥션 얻기 및 로그 출력
        try (Connection con = dataSource.getConnection()) {
            log.info("✅ DataSource 준비 완료");
            log.info(con); // 커넥션 객체 정보 출력
        }
    }

    @Autowired
    private SqlSessionFactory sqlSessionFactory; // MyBatis SqlSessionFactory 주입

    @Test
    public void testSqlSessionFactory() {
        try (
                // MyBatis 세션 열기
                SqlSession session = sqlSessionFactory.openSession();

                // 세션을 통해 JDBC 커넥션 획득
                Connection con = session.getConnection()
        ) {
            log.info(session); // 세션 정보 출력
            log.info(con);     // 커넥션 정보 출력
        } catch (Exception e) {
            fail(e.getMessage()); // 실패 시 예외 메시지 출력
        }
    }
}