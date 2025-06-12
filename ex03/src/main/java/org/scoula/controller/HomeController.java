package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 스프링이 컨트롤러로 인식하고 bean 등록
@Log4j2 // Lombok 로그 설정
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        // Model : Spring에서 제공하는 데이터 전달 객체
        // request scope에 데이터 저장
        model.addAttribute("name","홍길동");

        return "index";
    }
}
