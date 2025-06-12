package org.scoula.ex03.controller;

import org.scoula.ex03.dto.SampleDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/sample") // 공통 URL prefix
@Log4j2
public class SampleController {
    @RequestMapping("") // 모든 메소드 허용, /sample
    public void basic() {
        log.info("basic........");
    }

    // GET과 POST 모두 허용하는 특정 경로 매핑, /sample/basic
    @RequestMapping(value = "/basic",method = {RequestMethod.GET, RequestMethod.POST})
    public void basicGet(){
        log.info("basic get........");
    }

    // GET 전용 매핑, /sample/basicOnlyGet
    @GetMapping("/basicOnlyGet")
    public void basicGet2(){
        log.info("basic get only get........");
    }

    // /sample/ex01
    @GetMapping("/ex01")
    public String ex01(SampleDTO dto) {
        // dto의 기본 생성자와 setter 사용
        log.info(""+dto);
        return "ex01";
    }

    // /sample/ex02
    @GetMapping("/ex02")
    public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
        // DTO 객체를 사용하지 않고 파라미터를 각각 사용할때
        log.info("name:"+name);
        log.info("age:"+age);
        return "ex02";
    }

    // /sample/ex02List
    @GetMapping("/ex02List")
    public String ex02List(@RequestParam("ids")ArrayList<String> ids) {
        log.info("ids:"+ids);
        return "ex02List";
    }

    // /sample/ex02Array
    @GetMapping("/ex02Array")
    public String ex02Array(@RequestParam("ids") String[] ids) {
        log.info("array ids:"+ Arrays.toString(ids));

        return "ex02Array";
    }

    // /sample/ex04
    @GetMapping("/ex04")
    public String ex04(SampleDTO dto,@ModelAttribute("page") int page) {
        // DTO는 자동으로 뷰에 전달이 되지만 개별 파라미터 전달 시에는 ModelAttribute 사용
        log.info("dto: " + dto);   // SampleDTO 내부 필드 자동 바인딩
        log.info("page: " + page); // 일반 파라미터도 자동 바인딩
        return "sample/ex04";
    }

    // /sample/ex05
    @GetMapping("/ex05")
    public void ex05(){
        log.info("/ex05 호출됨....");
    }

    // /sample/ex06
    @GetMapping("/ex06")
    public String ex06(RedirectAttributes ra) {
        log.info("/ex06......");
        // redirect 대상에게 쿼리 스트링 전달
        ra.addAttribute("name","AAA");
        ra.addAttribute("age",10);

        return "redirect:/sample/ex06-2";
    }

    // /sample/ex07
    @GetMapping("/ex07")
    // ResponseBody : 반환된 객체를 뷰로 해석하지 않고 직접 출력
    public @ResponseBody SampleDTO ex07() {
        log.info("/ex07......");

        SampleDTO dto = new SampleDTO();
        dto.setName("홍길동");
        dto.setAge(10);

        return dto;
    }

    // /sample/ex08
    @GetMapping("/ex08")
    // ResponseEntity : 바디, 헤더, 상태코드를 객체에 담아서 넘긴다
    public ResponseEntity<String> ex08() {
        log.info("/ex08......");

        String msg = "{\"name\":\"홍길동\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<>(msg, headers, HttpStatus.OK);
    }

    // /sample/exUpload
    @GetMapping("/exUpload")
    public void exUpload(){
        log.info("/exUpload......");
    }

    // /sample/exUploadPost
    @PostMapping("/exUploadPost")
    public void exUploadPost(ArrayList<MultipartFile> files) {
        // 다중 파일 업로드 처리
        for (MultipartFile file : files) {
            log.info("--------------------------------------------------");
            log.info("name: " + file.getOriginalFilename()); // 파일 이름 출력 (한글 깨짐 주의)
            log.info("size: " + file.getSize());              // 바이트 단위 크기 출력
        }
    }
}
