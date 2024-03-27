package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.bad") // 두개 값을 response.sendError로 에러 내보냄
public class BadRequestException extends RuntimeException {
}
