package org.scoula.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.security.account.domain.CustomUser;
import org.scoula.security.account.domain.MemberVO;
import org.scoula.security.account.mapper.UserDetailsMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDetailsMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 데이터베이스에서 username에 해당하는 사용자 정보를 가져옴
        MemberVO vo = mapper.get(username);

        // 만약 사용자 정보가 존재하지 않으면 예외를 던짐
        if(vo == null) {
            throw new UsernameNotFoundException(username + "은 없는 id입니다.");
        }

        // 사용자 정보를 기반으로 CustomUser 객체를 생성하여 반환
        return new CustomUser(vo);
    }
}
