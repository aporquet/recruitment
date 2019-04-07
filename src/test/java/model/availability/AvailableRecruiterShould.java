package model.availability;

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
        RecruiterDto firstRecruiter = new RecruiterDto();
        LocalDateTime availableDate = LocalDateTime.of(2019, Month.JUNE, 2, 12, 30);
        firstRecruiter.setAvailability(availableDate);
        recruiters.add(firstRecruiter);
        RecruiterDto secondRecruiter = new RecruiterDto();
        LocalDateTime secondAvailableDate = LocalDateTime.of(2019, Month.JUNE, 2, 12, 30);
        firstRecruiter.setAvailability(secondAvailableDate);
        recruiters.add(secondRecruiter);
        AvailableRecruiter availableRecruiter = new AvailableRecruiter(recruiters);

        //When
        RecruiterDto firstAvailableRecruiter = availableRecruiter.getFirstAvailableRecruiter();

        //Then
        Assert.assertEquals(firstAvailableRecruiter, firstRecruiter);
    }
}
