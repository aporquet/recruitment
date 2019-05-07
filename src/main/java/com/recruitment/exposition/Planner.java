package com.recruitment.exposition;

import common.CandidatNotExistException;
import common.InterviewDateIsPriorThanCurrentDateException;
import org.springframework.web.bind.annotation.*;
import use_case.ScheduleInterview;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class Planner {

    private final ScheduleInterview repository;

    Planner(ScheduleInterview repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/Interviews", method = RequestMethod.GET)
    public String listInterviews(){
        return "Interviews list";
    }

    @PostMapping("/ScheduleInterview")
    public void interview (@RequestParam UUID idCandidat, LocalDateTime date){
        if(idCandidat.equals(0)){
            throw new CandidatNotExistException();
        }
        if(date.compareTo(LocalDateTime.now()) < 0){
            throw new InterviewDateIsPriorThanCurrentDateException();
        }
        repository.schedule(idCandidat);
    }



}