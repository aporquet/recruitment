package model.availability;

import common.dto.RecruiterDto;
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
        if (result.size() == 0){
            throw new AnyRecruiterAvailableInSameTimeAsTheCandidateException();
        }
        return result;
    }

    public RecruiterDto getFirstAvailableRecruiter(List<RecruiterDto> recruiters) {
        return recruiters.get(0);
    }

    boolean availabilityIsInCurrentMonth(Candidate candidate) {
        LocalDateTime currentDate =  LocalDateTime.now();
        int currentMonth = currentDate.getMonthValue();
        int monthAvailable = candidate.availability.getMonthValue();
        if(currentMonth != monthAvailable){
            throw new CandidateAvailabilityIsNotInCurrentMonthException();
        }
        return currentMonth == monthAvailable;
    }
}
