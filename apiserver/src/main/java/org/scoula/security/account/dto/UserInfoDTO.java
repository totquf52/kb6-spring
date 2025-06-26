package org.scoula.security.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import org.scoula.security.account.domain.MemberVO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {

    private String username;   // 사용자 ID
    private String email;      // 사용자 이메일
    private List<String> roles; // 권한 목록 (ex: ["ROLE_USER", "ROLE_ADMIN"])

    // MemberVO 객체를 UserInfoDTO로 변환하는 정적 팩토리 메서드
    public static UserInfoDTO of(MemberVO member) {
        return new UserInfoDTO(
                member.getUsername(),
                member.getEmail(),
                member.getAuthList().stream()
                        .map(a -> a.getAuth())  // 권한 문자열만 추출
                        .toList()
        );
    }
}
