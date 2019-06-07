package com.recruitment.exposition.command;

import com.recruitment.request.ScheduleInterviewRequest;
import infra.mysql.CandidateRepositoryImpl;
import infra.mysql.InterviewRepositoryImpl;
import infra.mysql.RecruitersRepositoryImpl;
import model.availability.AnyRecruiterAvailableException;
import model.availability.AnyRecruiterAvailableInSameTimeAsTheCandidateException;
import model.availability.CandidateAvailabilityIsNotInCurrentMonthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import use_case.ScheduleInterview;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class PlannerController {

    @PostMapping("/schedule")
    public ResponseEntity<ScheduleInterview> scheduleInterview (RequestEntity<ScheduleInterviewRequest> request) {
        ResponseEntity<ScheduleInterview> responseEntity = new ResponseEntity("", HttpStatus.NO_CONTENT);
        if(request.getBody().isValid()) {
            CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
            RecruitersRepositoryImpl recruitersRepository = new RecruitersRepositoryImpl();
            InterviewRepositoryImpl interviewRespository = new InterviewRepositoryImpl();
            LocalDateTime date = request.getBody().getDate();
            UUID candidateUuid = request.getBody().getIdCandidat();
            try{
                ScheduleInterview scheduler = new ScheduleInterview(candidateRepository, recruitersRepository, interviewRespository, date, candidateUuid);
                scheduler.schedule();
            }catch (AnyRecruiterAvailableException e){
                responseEntity.badRequest().body("Any recruiters are available");
                responseEntity.status(HttpStatus.NOT_FOUND);
            }catch (AnyRecruiterAvailableInSameTimeAsTheCandidateException e) {
                responseEntity.badRequest().body("Any recruiters are available in same time of the candidate availability");
                responseEntity.status(HttpStatus.NOT_FOUND);
            }catch (CandidateAvailabilityIsNotInCurrentMonthException e) {
                responseEntity.badRequest().body("Candidate availability is not in the current month");
                responseEntity.status(HttpStatus.BAD_REQUEST);
            }
            responseEntity.status(HttpStatus.ACCEPTED).body("Interview schedule is a success");;
        }
        return responseEntity;
    }

}