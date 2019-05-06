package com.recruitment.exposition;

import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.web.bind.annotation.*;
import use_case.ScheduleInterview;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class Planner {

    private final ScheduleInterview repository;

    Planner(ScheduleInterview repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/Interviews", method = RequestMethod.POST)
    public String listInterviews(){
        return "Interviews list";
    }

    @PostMapping("/ScheduleInterview")
    public ScheduleInterview interview (@RequestParam int idCandidat, LocalDateTime date){
        if(idCandidat <= 0){
            // TODO: create Error Common Class -> idCandidat is not valid
        }
        if(date.compareTo(LocalDateTime.now()) < 0){
            // TODO: create Error Common Class -> interview date is anterior than current date
        }
    }



}