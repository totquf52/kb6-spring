package org.scoula.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice // 모든 컨트롤러에서 발생하는 예외 처리
@Log4j2
public class CommonExceptionAdvice {
    @ExceptionHandler(Exception.class) // 모든 예외 처리
    public String except(Exception ex, Model model) {
        log.error("Exception 발생: " + ex.getMessage());
        // Model로 예외 객체를 뷰에 전달
        model.addAttribute("exception", ex);
        log.error(model);
        return "error_page";
    }

    @ExceptionHandler(NoHandlerFoundException.class) // 404 요청 전용 처리
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404(NoHandlerFoundException ex, Model model, HttpServletRequest request) {
        log.error(ex);
        // 어떤 경로에서 404가 발생했는지 뷰에 전달
        model.addAttribute("uri", request.getRequestURI());
        return "custom404";
    }
}
