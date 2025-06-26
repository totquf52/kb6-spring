package org.scoula.security.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class JsonResponse {

    // 일반 JSON 응답 전송 메서드 (ex: 로그인 성공 결과 등)
    public static <T> void send(HttpServletResponse response, T result) throws IOException {
        ObjectMapper om = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        Writer out = response.getWriter();
        // 자바 객체 -> JSON 직렬화
        out.write(om.writeValueAsString(result));
        out.flush();
    }

    // 에러 응답 전송 메서드 (예외 메시지 등 반환 시 사용)
    public static void sendError(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        response.setStatus(status.value()); // HTTP 상태 코드 설정
        response.setContentType("application/json;charset=UTF-8");

        Writer out = response.getWriter();
        out.write(message); // message는 보통 단순 문자열 (혹은 직접 JSON 문자열로 구성)
        out.flush();
    }
}