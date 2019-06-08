package com.recruitment.exposition.query;

import common.dto.InterviewFullDto;
import infra.mysql.InterviewRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/interviews")
public class InterviewController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InterviewFullDto>  getInterviews (){
            InterviewRepositoryImpl interviewRepository = new InterviewRepositoryImpl();
            List<InterviewFullDto> interviewFullDtos = interviewRepository.getInterviews();
            return interviewFullDtos;
        }

}
