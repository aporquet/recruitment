package model.availability;

import common.RecruiterDto;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class AvailableRecruiterShould {

    @Test
    public void should_return_the_first_available_recruiter(){
        //Given
        List<RecruiterDto> recruiters = new ArrayList<>();
        List< LocalDateTime > firstRecruiterAvailabilities = new ArrayList<>();
        LocalDateTime firstRecruiterAvailability = LocalDateTime.of(2019, Month.JUNE, 2, 12, 30);
        firstRecruiterAvailabilities.add(firstRecruiterAvailability);
        RecruiterDto firstRecruiter = new RecruiterDto(1, firstRecruiterAvailabilities, null, 2);
        LocalDateTime availableDate = LocalDateTime.of(2019, Month.JUNE, 2, 12, 30);
        firstRecruiter.setAvailability(availableDate);
        recruiters.add(firstRecruiter);
        RecruiterDto secondRecruiter = new RecruiterDto(1, firstRecruiterAvailabilities, null, 2);
        LocalDateTime secondAvailableDate = LocalDateTime.of(2019, Month.JUNE, 2, 12, 30);
        firstRecruiter.setAvailability(secondAvailableDate);
        recruiters.add(secondRecruiter);
        AvailableRecruiter availableRecruiter = new AvailableRecruiter(recruiters);

        //When
        RecruiterDto firstAvailableRecruiter = availableRecruiter.getFirstAvailableRecruiter();

        //Then
        Assert.assertEquals(firstAvailableRecruiter, firstRecruiter);

    }

    @Test(expected = AnyRecruiterAvailableException.class)
    public void throw_any_recruiter_available_exception_when_recruiters_list_is_empty(){
        //Given
        AvailableRecruiter availableRecruiter = new AvailableRecruiter(new ArrayList<>());

        //When
        availableRecruiter.getFirstAvailableRecruiter();

    }

    @Test(expected = AnyRecruiterAvailableException.class)
    public void throw_any_recruiter_available_exception_when_recruiter_is_null(){
        //Given
        AvailableRecruiter availableRecruiter = new AvailableRecruiter(null);

        //When
        availableRecruiter.getFirstAvailableRecruiter();

    }


}
