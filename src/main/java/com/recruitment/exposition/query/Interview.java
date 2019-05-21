package com.recruitment.exposition.query;

import org.springframework.web.bind.annotation.GetMapping;

public class Interview {

    @GetMapping("/interviews")
    public String getInterviews (){
        return "Interviews";
    }

    /*TODO: CRUD INTERVIEWS*/

}
