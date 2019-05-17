package com.recruitment;

import common.CandidatNotExistException;
import common.InterviewDateIsPriorThanCurrentDateException;
import infra.CandidateRepositoryImpl;
import infra.InterviewRepositoryImpl;
import infra.RecruitersRepositoryImpl;
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
    public void scheduleInterview (@RequestParam UUID idCandidat, LocalDateTime date){
        if(idCandidat == null){
            throw new CandidatNotExistException();
        }
        if(date.compareTo(LocalDateTime.now()) <= 0){
            throw new InterviewDateIsPriorThanCurrentDateException();
        }
        scheduler.schedule(idCandidat);
    }

}