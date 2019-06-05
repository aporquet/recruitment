package com.recruitment.exposition.query;

import com.recruitment.request.GetInterviewsRequest;
import infra.mysql.InterviewRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import use_case.ScheduleInterview;

@RestController
@RequestMapping(value = "/interviews")
public class InterviewController {

    @GetMapping("")
    public ResponseEntity<ScheduleInterview> getInterviews (RequestEntity<GetInterviewsRequest> request){
        ResponseEntity<ScheduleInterview> responseEntity = new ResponseEntity("", HttpStatus.NO_CONTENT);
        if (request.getBody().isValid()){
            InterviewRepositoryImpl interviewRepository = new InterviewRepositoryImpl();
            String result = interviewRepository.getInterviews();
            responseEntity.status(HttpStatus.ACCEPTED);
            responseEntity.accepted().body(result);
        }
        else {
            responseEntity.status(HttpStatus.BAD_REQUEST);
            responseEntity.badRequest().body("GetInterviewRequest is not valid");
        }
        return responseEntity;
    }

}
