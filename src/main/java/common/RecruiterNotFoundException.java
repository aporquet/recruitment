package common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK, reason = "Recruiter not found")
public class RecruiterNotFoundException extends RuntimeException {
}
