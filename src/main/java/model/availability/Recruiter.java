package model.availability;

import java.time.LocalDateTime;
import java.util.List;

public class Recruiter {

    private List<LocalDateTime> availabilities;

    public Recruiter(List<LocalDateTime> firstRecruiterAvailabilities) {
        this.availabilities = firstRecruiterAvailabilities;
    }

    public List<LocalDateTime> availabilities() {
        return availabilities;
    }
}
