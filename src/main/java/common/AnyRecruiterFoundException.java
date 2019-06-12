package common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK, reason = "Any Recruiters found")
public class AnyRecruiterFoundException extends RuntimeException {
}
