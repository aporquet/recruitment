package model.availability;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK, reason = "Any availability date")
public class AnyAvailabilityDateException extends RuntimeException {
}
