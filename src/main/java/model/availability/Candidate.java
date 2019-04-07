package model.availability;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class Candidate {

    private LocalDateTime availability;

    Candidate(LocalDateTime availability) {
        this.availability = availability;
    }

    List<RecruiterDto> findAvailableRecruiters(List<RecruiterDto> recruiters) {
        List<RecruiterDto> result = new ArrayList<>();
        for (RecruiterDto recruiter: recruiters) {
            for (LocalDateTime recruiterAvailability : recruiter.getRecruiterAvailabilities()){
                if (recruiterAvailability.equals(availability)) {
                    result.add(recruiter);
                }
            }
        }
        return result;
    }

    boolean availabilityIsInCurrentMonth(Candidate candidate) {
        LocalDateTime currentDate =  LocalDateTime.now();
        int currentMonth = currentDate.getMonthValue();
        int monthAvailable = candidate.availability.getMonthValue();
        return currentMonth == monthAvailable;
    }
}
