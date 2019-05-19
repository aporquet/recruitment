package com.recruitment.exposition.query;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

public class Interview {

    @GetMapping("/interviews")
    public String getInterviews (){
        return "Interviews";
    }

}
