package common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK, reason = "You cannot schedule interview at this date, please select a date between tomorrow and next month")
public class InvalidDateToScheduleInterviewException extends RuntimeException {
}
