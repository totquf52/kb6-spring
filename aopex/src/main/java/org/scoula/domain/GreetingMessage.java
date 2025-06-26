package org.scoula.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter, Setter, toString, equals, hashCode 자동 생성
@AllArgsConstructor // 모든 필드를 받는 생성자 자동 생성
@NoArgsConstructor  // 기본 생성자 자동 생성
public class GreetingMessage {

    private String name; // 클라이언트가 보낸 사용자 이름
}