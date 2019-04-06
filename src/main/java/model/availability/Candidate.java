package model.availability;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Candidate {

    private LocalDateTime availability;

    public Candidate(LocalDateTime availability) {
        this.availability = availability;
    }

    public List<Recruiter> findAvailableRecruiters(List<Recruiter> recruiters) {
        List<Recruiter> result = new ArrayList<>();
        for (Recruiter recruiter: recruiters) {
            for (LocalDateTime recruiterAvailability : recruiter.availabilities()){
                if (recruiterAvailability.equals(availability)) {
                    result.add(recruiter);
                }
            }
        }
        return result;
    }

    public boolean availabilityIsInCurrentMonth(Candidate candidate) {
        LocalDateTime currentDate =  LocalDateTime.now();
        int currentMonth = currentDate.getMonthValue();
        int monthAvailable = candidate.availability.getMonthValue();
            if (currentMonth == monthAvailable) {
                return true;
            }
        return false;
    }
}
