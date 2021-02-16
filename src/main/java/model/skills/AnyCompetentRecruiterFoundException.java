package model.skills;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK, reason = "Any competent recruiter was found to test this candidate, " +
        "key skills not matching with candidate key skill or candidate experience year is prior than recruiters")
public class AnyCompetentRecruiterFoundException extends RuntimeException{
}
