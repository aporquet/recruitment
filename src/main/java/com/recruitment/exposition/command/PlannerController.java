package com.recruitment.exposition.command;

import infra.mysql.CandidateRepositoryImpl;
import infra.mysql.InterviewRepositoryImpl;
import infra.mysql.RecruitersRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import use_case.ScheduleInterview;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class PlannerController {

    @PostMapping("/schedule")
    @ResponseStatus(HttpStatus.OK)
    public void scheduleInterview(@RequestParam UUID uuidCandidate, String stringDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime dateTime = LocalDateTime.parse(stringDateTime, formatter);
        CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
        RecruitersRepositoryImpl recruitersRepository = new RecruitersRepositoryImpl();
        InterviewRepositoryImpl interviewRespository = new InterviewRepositoryImpl();
        ScheduleInterview scheduler = new ScheduleInterview(candidateRepository, recruitersRepository, interviewRespository, dateTime, uuidCandidate);
        scheduler.schedule();
    }

}