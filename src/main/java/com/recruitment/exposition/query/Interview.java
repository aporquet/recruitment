package com.recruitment.exposition.query;

import org.springframework.web.bind.annotation.GetMapping;

public class Interview {

    /*TODO: CRUD INTERVIEWS*/

    @GetMapping("/interviews")
    public String getInterviews (){
        return "Interviews";
    }

}
