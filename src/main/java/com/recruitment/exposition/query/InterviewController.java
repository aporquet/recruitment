package com.recruitment.exposition.query;

import common.dto.InterviewDeleterDto;
import common.dto.InterviewFullDto;
import infra.mysql.InterviewRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/interviews",  method = {RequestMethod.GET, RequestMethod.DELETE})
public class InterviewController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InterviewFullDto> getInterviews() {
        InterviewRepositoryImpl interviewRepository = new InterviewRepositoryImpl();
        List<InterviewFullDto> interviewFullDtos = interviewRepository.getInterviews();
        return interviewFullDtos;
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInterview(@RequestBody InterviewDeleterDto interviewDeleterDto) {
        InterviewRepositoryImpl interviewRepository = new InterviewRepositoryImpl();
        interviewRepository.deleteInterview(interviewDeleterDto);
    }

}
