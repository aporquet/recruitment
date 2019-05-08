package com.recruitment.exposition;

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

    @RequestMapping(value = "/Interviews", method = RequestMethod.GET)
    public String listInterviews(){
        return "Interviews list";
    }

    @PostMapping("/ScheduleInterview")
    public void scheduleInterview (@RequestParam UUID idCandidat, LocalDateTime date){
        if(idCandidat.equals(0)){
            throw new CandidatNotExistException();
        }
        if(date.compareTo(LocalDateTime.now()) < 0){
            throw new InterviewDateIsPriorThanCurrentDateException();
        }
        scheduler.schedule(idCandidat);
    }

}