package com.recruitment.exposition.command;

import com.recruitment.request.ScheduleInterviewRequest;
import infra.CandidateRepositoryImpl;
import infra.InterviewRepositoryImpl;
import infra.RecruitersRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import use_case.ScheduleInterview;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class Planner {

    private CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
    private RecruitersRepositoryImpl recruitersRepository = new RecruitersRepositoryImpl();
    private InterviewRepositoryImpl interviewRespository = new InterviewRepositoryImpl();
    private LocalDateTime date;
    private UUID candidateId;

    private ScheduleInterview scheduler = new ScheduleInterview(candidateRepository, recruitersRepository, interviewRespository, date, candidateId);

    @PostMapping("/schedule")
    public ResponseEntity<ScheduleInterview> scheduleInterview (RequestEntity<ScheduleInterviewRequest> request) {
        ResponseEntity<ScheduleInterview> responseEntity = new ResponseEntity("", HttpStatus.NO_CONTENT);
        if(request.getBody().isValid()) {
            if (request.getBody().getIdCandidat() == null) {
                responseEntity.badRequest().body("Candidate doesn't exist");
                responseEntity.status(HttpStatus.BAD_REQUEST);
            }
            if (date.compareTo(LocalDateTime.now()) <= 0) {
                responseEntity.badRequest().body("Interview date is prior than current date exception");
                responseEntity.status(HttpStatus.BAD_REQUEST);
            }
            scheduler.schedule(request.getBody().getIdCandidat());
            responseEntity.status(HttpStatus.ACCEPTED);
            responseEntity.badRequest().body("Interview schedule is a success");
        }
        return responseEntity;
    }

}