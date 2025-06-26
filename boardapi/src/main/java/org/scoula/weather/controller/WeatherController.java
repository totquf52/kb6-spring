package org.scoula.weather.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.weather.dto.WeatherDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.context.annotation.PropertySource;

@Controller
@Log4j2
@RequestMapping("/weather")
// properties 파일에서 값 불러옴
@PropertySource({"classpath:/application.properties"})
public class WeatherController {

    // application.properties에서 키의 값을 주입
    @Value("${weather.url}")
    private String URL;

    @Value("${weather.icon_url}")
    private String ICON_URL;

    @Value("${weather.api_key}")
    private String API_KEY;

    @GetMapping({"", "/{city}"}) // "/weather", "/weather/서울" 같은 요청 처리
    public String weather(Model model,
                          @PathVariable(value = "city", required = false) String city) {

        // 도시가 없으면 기본값은 "seoul"
        city = city == null ? "seoul" : city;

        // 외부 API 호출 객체 생성
        RestTemplate restTemplate = new RestTemplate();

        // 쿼리 파라미터를 포함한 최종 요청 URL 구성
        String url = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("q", city)         // 도시 이름
                .queryParam("units", "metric") // 섭씨
                .queryParam("APPID", API_KEY)  // API 키
                .queryParam("lang", "kr")      // 한국어
                .toUriString();                // 최종 URL 문자열로 변환

        // 외부 API 호출 후 JSON → WeatherDTO로 매핑
        WeatherDTO weather = restTemplate.getForObject(url, WeatherDTO.class);

        // 날씨 아이콘 URL 완성 (e.g. http://.../%s.png → 실제 아이콘 이름 넣기)
        String iconUrl = ICON_URL.formatted(weather.getWeather().get(0).getIcon());

        log.info("오늘의 날씨: " + weather);

        // 뷰에 전달할 데이터 세팅
        model.addAttribute("city", city);
        model.addAttribute("weather", weather);
        model.addAttribute("iconUrl", iconUrl);

        // /WEB-INF/views/weather/today.jsp 로 이동
        return "weather/today";
    }
}