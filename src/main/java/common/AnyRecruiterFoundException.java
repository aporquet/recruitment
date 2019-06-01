package common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Recruiters not found")
public class AnyRecruiterFoundException extends RuntimeException {
}
