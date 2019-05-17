package com.recruitment.exposition;

import com.recruitment.request.BaseResponse;
import com.recruitment.request.ScheduleInterviewRequest;
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

    @GetMapping("/interviews")
    public String getInterviews (){
        return "Interviews";
    }

    @PostMapping("/schedule")
    public void scheduleInterview (ScheduleInterviewRequest req, BaseResponse res) {
        if(req.getIdCandidat() == null){
            throw new CandidatNotExistException();
        }
        if(date.compareTo(LocalDateTime.now()) <= 0){
            throw new InterviewDateIsPriorThanCurrentDateException();
        }
        scheduler.schedule(req.getIdCandidat());
    }

}