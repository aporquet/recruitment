package com.recruitment.exposition;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Planner {

    @RequestMapping(value = "/Interviews", method = RequestMethod.GET)
    public String listInterviews(){
        return "Interviews list";
    }

    @RequestMapping(value = "/testSpring", method = RequestMethod.GET)
    public String test(){
        return "Run !!";
    }
}