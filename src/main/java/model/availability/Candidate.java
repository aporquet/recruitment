package model.availability;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Candidate {
    private List<LocalDateTime> availabilities;

    Candidate(List<LocalDateTime> availabilities) {
        this.availabilities = availabilities;
    }

    List<Recruiter> findAvailableRecruiters(List<Recruiter> recruiters) {
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

    boolean availabilitiesAreInCurrentMonth(Candidate candidate) {
        LocalDateTime currentDate =  LocalDateTime.now();
        int currentMonth = currentDate.getMonthValue();
        for(LocalDateTime candidateAvailability: candidate.availabilities){
            int monthAvailable = candidateAvailability.getMonthValue();
            if (currentMonth == monthAvailable) {
                return true;
            }
        }
        return false;
    }
}
