package org.scoula.persistence;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.fail;

@Log4j2
public class JDBCTests {

    @BeforeAll // 클래스 단위로 모든 테스트 전에 한번 실행
    public static void setup() {
        try {
            // MySQL JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace(); // 드라이버 로딩 실패 시 예외 출력
        }
    }

    @Test
    @DisplayName("JDBC 드라이버 연결이 된다.")
    public void testConnection() {
        String url = "jdbc:mysql://localhost:3306/scoula_db";

        try (Connection con = DriverManager.getConnection(url, "scoula", "1234")) {
            log.info(con); // 연결 성공 로그 출력
        } catch (Exception e) {
            fail(e.getMessage()); // 연결 실패 시 테스트 실패 처리
        }
    }
}