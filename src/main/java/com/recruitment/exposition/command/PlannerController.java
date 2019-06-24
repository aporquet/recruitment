package com.recruitment.exposition.command;

import common.dto.ScheduleInterviewDto;
import common.exceptions.InvalidDateToScheduleInterviewException;
import infra.mysql.CandidateRepositoryImpl;
import infra.mysql.InterviewRepositoryImpl;
import infra.mysql.RecruitersRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import use_case.ScheduleInterview;

import java.time.LocalDateTime;
import java.util.UUID;

@CrossOrigin()
@RestController
@RequestMapping(method = {RequestMethod.POST})
public class PlannerController {

    @PostMapping("/schedule")
    @ResponseStatus(HttpStatus.OK)
    public void scheduleInterview(@RequestBody ScheduleInterviewDto scheduleInterviewDto){
        UUID uuidCandidate = scheduleInterviewDto.getUuidCandidate();
        LocalDateTime dateInterview = scheduleInterviewDto.getDateInterview();
        if ((dateInterview.compareTo(LocalDateTime.now()) <= 0) ||
                (dateInterview.isAfter(LocalDateTime.now().plusMonths(1)))){
            throw new InvalidDateToScheduleInterviewException();
        }
        CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
        RecruitersRepositoryImpl recruitersRepository = new RecruitersRepositoryImpl();
        InterviewRepositoryImpl interviewRespository = new InterviewRepositoryImpl();
        ScheduleInterview scheduler = new ScheduleInterview(candidateRepository, recruitersRepository, interviewRespository, dateInterview, uuidCandidate);
        scheduler.schedule();
    }
}