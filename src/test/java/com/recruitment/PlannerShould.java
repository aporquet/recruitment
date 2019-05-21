package com.recruitment;

import com.recruitment.exposition.command.Planner;
import com.recruitment.request.ScheduleInterviewRequest;
import infra.CandidateRepositoryImpl;
import infra.InterviewRepositoryImpl;
import infra.RecruitersRepositoryImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import use_case.ScheduleInterview;
import java.time.LocalDateTime;
import java.util.UUID;

public class PlannerShould {

    private CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
    private RecruitersRepositoryImpl recruitersRepository = new RecruitersRepositoryImpl();
    private InterviewRepositoryImpl interviewRespository = new InterviewRepositoryImpl();
    private LocalDateTime date;
    private UUID candidateId;

    private ScheduleInterview scheduler = new ScheduleInterview(candidateRepository, recruitersRepository, interviewRespository, date, candidateId);

    @Mock RequestEntity<ScheduleInterviewRequest> requestEntity;

/*    @Test
    public ResponseEntity<ScheduleInterview> return_candidate_not_exist_error_if_idCandidate_is_null(){
        Planner planner = new Planner();
        date = LocalDateTime.now().plusMonths(1);
        ScheduleInterviewRequest scheduleInterviewRequest = new ScheduleInterviewRequest(candidateId, date);
        RequestEntity<ScheduleInterviewRequest> requestEntity = new RequestEntity<>(

        );
        ResponseEntity responseEntity = planner.scheduleInterview(requestEntity);
        Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST);
    }

    @Test
    public void return_interview_date_is_prior_than_current_date_error_if_date_is_equal_or_prior_than_current_date(){
        Planner planner = new Planner();
        date = LocalDateTime.now();
        candidateId = UUID.randomUUID();
        planner.scheduleInterview(candidateId, date);
    }

    *//*INTEGRATION *//*
    @Test public void schedule_interview(){
        return;
    }*/
}
