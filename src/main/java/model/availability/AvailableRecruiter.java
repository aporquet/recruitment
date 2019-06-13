package model.availability;

import common.dto.RecruiterDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AvailableRecruiter {

    private List<RecruiterDto> recruiters;

    public AvailableRecruiter(List<RecruiterDto> recruiters) {
        if (recruiters == null || recruiters.isEmpty()) {
            throw new AnyRecruiterAvailableException();
        }
        this.recruiters = recruiters;
    }

    public RecruiterDto getAvailableRecruiter(LocalDateTime dateAvailability) {
        if(dateAvailability == null){
            throw new AnyAvailabilityDateException();
        }
        Candidate candidate = new Candidate(dateAvailability);
        List<RecruiterDto> availableRecruiters;
        availableRecruiters = candidate.findAvailableRecruiters(recruiters);
        RecruiterDto availableRecruiter = candidate.getFirstAvailableRecruiter(availableRecruiters);
        return availableRecruiter;
    }

}
