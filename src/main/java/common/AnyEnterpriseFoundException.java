package common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK, reason = "Any enterprises found")
public class AnyEnterpriseFoundException extends RuntimeException {
}
