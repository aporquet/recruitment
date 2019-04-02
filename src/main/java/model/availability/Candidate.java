package model.availability;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Candidate {
    private List<LocalDateTime> availabilities;

    public Candidate(List<LocalDateTime> availabilities) {
        this.availabilities = availabilities;
    }

    public List<Recruiter> findAvailableRecruiters(List<Recruiter> recruiters) {
        List<Recruiter> result = new ArrayList<>();
        for (Recruiter recruiter: recruiters) {
            for (LocalDateTime recruiterAvailability : recruiter.availabilities()){
                if (availabilities.contains(recruiterAvailability)) {
                    result.add(recruiter);
                }
            }
        }
        return result;
    }
}
