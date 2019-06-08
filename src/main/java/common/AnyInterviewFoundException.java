package common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK, reason = "Interviews not found")
public class AnyInterviewFoundException extends RuntimeException {
}
