package model.availability;

import java.time.LocalDateTime;
import java.util.List;

class Recruiter {

    private List<LocalDateTime> availabilities;

    Recruiter(List<LocalDateTime> firstRecruiterAvailabilities) {
        this.availabilities = firstRecruiterAvailabilities;
    }

    List<LocalDateTime> availabilities() {
        return availabilities;
    }
}
