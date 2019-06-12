package common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK, reason = "Any candidates found")
public class AnyCandidateFoundException extends RuntimeException {
}
