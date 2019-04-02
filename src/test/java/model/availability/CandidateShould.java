package model.availability;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class CandidateShould {

    @Test
    public void find_recruiters_for_its_availability_slots(){
        //Given
        List< LocalDateTime > availabilities = new ArrayList<>();
        LocalDateTime firstAvailability = LocalDateTime.of(2019, Month.JUNE, 2, 12, 30);
        availabilities.add(firstAvailability);
        Candidate candidate = new Candidate(availabilities);

        List<Recruiter> recruiters = new ArrayList<>();
        List< LocalDateTime > firstRecruiterAvailabilities = new ArrayList<>();
        LocalDateTime firstRecruiterAvailability = LocalDateTime.of(2019, Month.JUNE, 2, 12, 30);
        firstRecruiterAvailabilities.add(firstRecruiterAvailability);
        Recruiter firstRecruiter = new Recruiter(firstRecruiterAvailabilities);
        recruiters.add(firstRecruiter);

        //When
        List<Recruiter> availableRecruiters = candidate.findAvailableRecruiters(recruiters);

        //Then
        Assert.assertTrue(availableRecruiters.contains(firstRecruiter));

    }

}
