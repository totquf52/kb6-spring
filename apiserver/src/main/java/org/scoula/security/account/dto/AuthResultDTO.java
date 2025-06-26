package org.scoula.security.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResultDTO {
    private String token;         // 발급된 JWT 토큰
    private UserInfoDTO user;     // 로그인한 사용자 정보 (username, email, roles)
}