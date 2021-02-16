package model.availability;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK, reason = "Candidate availability is not in current month")
public class CandidateAvailabilityIsNotInCurrentMonthException extends RuntimeException {
}
