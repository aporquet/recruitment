package model.availability;

import common.RecruiterDto;
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

        List<RecruiterDto> recruiters = new ArrayList<>();
        List< LocalDateTime > firstRecruiterAvailabilities = new ArrayList<>();
        LocalDateTime firstRecruiterAvailability = LocalDateTime.of(2019, Month.JUNE, 2, 12, 30);
        firstRecruiterAvailabilities.add(firstRecruiterAvailability);
        RecruiterDto firstRecruiter = new RecruiterDto();
        firstRecruiter.setAvailabilities(firstRecruiterAvailabilities);
        recruiters.add(firstRecruiter);

        //When
        List<RecruiterDto> availableRecruiters = candidate.findAvailableRecruiters(recruiters);

        //Then
        Assert.assertTrue(availableRecruiters.contains(firstRecruiter));

    }

    @Test
            (expected = AnyRecruiterAvailableInSameTimeAsTheCandidateException.class)
    public void not_find_recruiters_if_their_availabilities_slots_are_different(){
        //Given
        LocalDateTime availability = LocalDateTime.of(2019, Month.JUNE, 2, 12, 30);
        Candidate candidate = new Candidate(availability);

        List<RecruiterDto> recruiters = new ArrayList<>();
        List< LocalDateTime > firstRecruiterAvailabilities = new ArrayList<>();
        LocalDateTime firstRecruiterAvailability = LocalDateTime.of(2019, Month.JUNE, 3, 12, 30);
        LocalDateTime secondRecruiterAvailability = LocalDateTime.of(2019, Month.JUNE, 4, 9, 30);
        firstRecruiterAvailabilities.add(firstRecruiterAvailability);
        firstRecruiterAvailabilities.add(secondRecruiterAvailability);
        RecruiterDto firstRecruiter = new RecruiterDto();
        firstRecruiter.setAvailabilities(firstRecruiterAvailabilities);
        recruiters.add(firstRecruiter);

        //When
        List<RecruiterDto> availableRecruiters = candidate.findAvailableRecruiters(recruiters);

        //Then
        Assert.assertFalse(availableRecruiters.contains(firstRecruiter));
    }

    @Test
            (expected = CandidateAvailabilityIsNotInCurrentMonthException.class)
    public void not_find_recruiters_available_if_his_availability_is_not_in_the_current_month(){
        //Given
        LocalDateTime currentDate =  LocalDateTime.now();
        int CandidateMonthAvailability = currentDate.getMonthValue() + 1;
        LocalDateTime availability = LocalDateTime.of(2019, CandidateMonthAvailability, 13, 18, 30);
        Candidate candidate = new Candidate(availability);

        //When
        boolean IsCurrentMonth = candidate.availabilityIsInCurrentMonth(candidate);

        //Then
        Assert.assertFalse(IsCurrentMonth);
    }

    @Test
    public void find_recruiters_available_if_his_availability_is_in_the_current_month(){
        //Given
        LocalDateTime currentDate =  LocalDateTime.now();
        int CandidateMonthAvailability = currentDate.getMonthValue();
        LocalDateTime availability = LocalDateTime.of(2019, CandidateMonthAvailability, 13, 18, 30);
        Candidate candidate = new Candidate(availability);

        //When
        boolean IsCurrentMonth = candidate.availabilityIsInCurrentMonth(candidate);

        //Then
        Assert.assertTrue(IsCurrentMonth);
    }

}
