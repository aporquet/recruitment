package model.availability;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK, reason = "Any recruiters are available")
public class AnyRecruiterAvailableException extends RuntimeException {

}
