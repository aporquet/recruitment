package com.recruitment;

import common.CandidatNotExistException;
import common.InterviewDateIsPriorThanCurrentDateException;
import org.junit.Test;
import use_case.ScheduleInterview;
import java.time.LocalDateTime;
import java.util.UUID;

public class PlannerShould {

    private ScheduleInterview scheduleInterview;

    @Test(expected = CandidatNotExistException.class)
    public void return_CandidatNotExistException_if_idCandidate_is_null(){
        Planner planner = new Planner();
        LocalDateTime date = LocalDateTime.now().plusMonths(1);
        UUID idCandidate = null;
        planner.scheduleInterview(idCandidate, date);
    }

    @Test(expected = InterviewDateIsPriorThanCurrentDateException.class)
    public void return_InterviewDateIsPriorThanCurrentDateException_if_date_is_equal_or_prior_than_current_date(){
        Planner planner = new Planner();
        LocalDateTime date = LocalDateTime.now();
        UUID idCandidate = UUID.randomUUID();
        planner.scheduleInterview(idCandidate, date);
    }

/*    @Test
    public void call_application_layer_if_parameters_are_valid(){
        scheduleInterview = mock(ScheduleInterview.class);
        Planner planner = new Planner();
        LocalDateTime date = LocalDateTime.now().plusMonths(1);;
        UUID idCandidate = UUID.randomUUID();
        planner.scheduleInterview(idCandidate, date);
        verify(scheduleInterview).schedule(Mockito.any(UUID.class));
    }*/
}
