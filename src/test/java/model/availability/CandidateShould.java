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
        LocalDateTime availability = LocalDateTime.of(2019, Month.JUNE, 2, 12, 30);
        Candidate candidate = new Candidate(availability);

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

    @Test
    public void not_find_recruiters_if_their_availabilities_slots_are_different(){
        //Given
        LocalDateTime availability = LocalDateTime.of(2019, Month.JUNE, 2, 12, 30);
        Candidate candidate = new Candidate(availability);

        List<Recruiter> recruiters = new ArrayList<>();
        List< LocalDateTime > firstRecruiterAvailabilities = new ArrayList<>();
        LocalDateTime firstRecruiterAvailability = LocalDateTime.of(2019, Month.JUNE, 3, 12, 30);
        firstRecruiterAvailabilities.add(firstRecruiterAvailability);
        Recruiter firstRecruiter = new Recruiter(firstRecruiterAvailabilities);
        recruiters.add(firstRecruiter);

        //When
        List<Recruiter> availableRecruiters = candidate.findAvailableRecruiters(recruiters);

        //Then
        Assert.assertFalse(availableRecruiters.contains(firstRecruiter));
    }

    @Test
    public void not_find_recuiters_available_if_his_availability_is_not_in_the_current_month(){
        //Given
        LocalDateTime availability = LocalDateTime.of(2019, Month.DECEMBER, 13, 18, 30);
        Candidate candidate = new Candidate(availability);

        //When
        boolean IsCurrentMonth = candidate.availabilityIsInCurrentMonth(candidate);

        //Then
        Assert.assertFalse(IsCurrentMonth);
    }


}
