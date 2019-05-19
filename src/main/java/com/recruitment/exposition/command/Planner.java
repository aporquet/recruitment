package com.recruitment.exposition.command;

import com.fasterxml.jackson.databind.*;
import com.recruitment.request.ScheduleInterviewRequest;
import com.recruitment.response.ScheduleInterviewResponse;
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
    public void scheduleInterview (ScheduleInterviewRequest request, ScheduleInterviewResponse response) {
        if(request.getIdCandidat() == null){
            response.setStatus("404");
            throw new CandidatNotExistException();
        }
        if(date.compareTo(LocalDateTime.now()) <= 0){
            response.setStatus("404");
            throw new InterviewDateIsPriorThanCurrentDateException();
        }
        response.setStatus("200");
        response.setType("application/json");
        //TODO : Check conditions
        response.work();
        scheduler.schedule(request.getIdCandidat());
    }

    private void mapRequestDatas(ScheduleInterviewRequest request) {
        try {
            if (request.isValid()){
                ObjectMapper mapper = new ObjectMapper();
                request.setDate(mapper.readValue(, LocalDateTime.class));
                request.setIdCandidat(mapper.readValue(, UUID.class));
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        }
    }

}