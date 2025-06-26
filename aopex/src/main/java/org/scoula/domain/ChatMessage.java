package org.scoula.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

    private String name;    // 메시지 보낸 사용자 이름
    private String content; // 메시지 내용
}