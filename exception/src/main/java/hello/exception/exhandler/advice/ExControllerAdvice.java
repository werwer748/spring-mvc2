package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
/**
 * @RestControllerAdvice` 는 `@ControllerAdvice` 와 같고, `@ResponseBody` 가 추가되어 있다.
 * 특정 컨트롤러 지정하여 사용하는 방법 - 대상 지정하지 않으면 글로벌
 * 1. @ControllerAdvice(annotations = RestController.class)
 * 2. @ControllerAdvice("org.example.controllers")
 * 3. @ControllerAdvice(assignableTypes = {ControllerInterface.class, AbstractController.class})
 */
@RestControllerAdvice(basePackages = "hello.exception.api")
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 2. 정상 응답 흐름에 상태코드를 원하는걸로 지정
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);
        // 1. 정상 흐름으로 바꿔서 리턴하기 때문에 상태코드가 200이 됨.
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler // ResponseEntity도 쓸 수 있다.
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 정상 응답 흐름에 상태코드를 원하는걸로 지정
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }

}
