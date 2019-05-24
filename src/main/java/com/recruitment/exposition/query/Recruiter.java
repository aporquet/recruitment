package com.recruitment.exposition.query;

import org.springframework.web.bind.annotation.GetMapping;

public class Recruiter  {

    // TODO: Get query recruiters requests

    @GetMapping("/recruiters")
    public String getRecruiters (){
        return "Recruiters";
    }

    @GetMapping("/recruiters/id")
    public String getRecruiter (){
        return "Recruiter information";
    }
}
