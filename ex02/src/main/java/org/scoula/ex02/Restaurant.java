package org.scoula.ex02;

import org.springframework.beans.factory.annotation.Autowired; // 스프링 의존성 주입
import org.springframework.stereotype.Component;             // 스프링 빈 등록

import lombok.Data;
import lombok.Setter;

@Component              // 스프링이 이 클래스를 빈으로 등록
@Data
public class Restaurant {

    @Autowired          // 스프링이 Chef 타입의 빈을 자동 주입
    private Chef chef;
}