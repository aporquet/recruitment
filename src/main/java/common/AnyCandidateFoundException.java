package common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK, reason = "Candidates not found")
public class AnyCandidateFoundException extends RuntimeException {
}
