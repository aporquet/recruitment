package common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK, reason = "Candidate is not set for update")
public class CannotUpdateNullCandidateException extends RuntimeException {
}
