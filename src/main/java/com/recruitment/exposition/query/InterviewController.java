package com.recruitment.exposition.query;

import common.dto.InterviewFullDto;
import infra.mysql.InterviewRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
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

    @DeleteMapping("/delete/{idInterview}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteInterview(@PathVariable String idInterview) {
        int idInterviewToDelete = Integer.parseInt(idInterview);
        InterviewRepositoryImpl interviewRepository = new InterviewRepositoryImpl();
        return interviewRepository.deleteInterview(idInterviewToDelete);
    }

}
