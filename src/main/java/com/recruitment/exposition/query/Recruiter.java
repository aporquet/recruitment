package com.recruitment.exposition.query;

import common.RecruiterFullDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import infra.mySQL.RecruitersRepositoryImpl;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Recruiter {

    @GetMapping("/recruiters/id")
    public ResponseEntity<String> getRecruiter() {
        ResponseEntity<List<RecruiterFullDto>> responseEntity = new ResponseEntity("", HttpStatus.NO_CONTENT);
        return responseEntity.status(HttpStatus.ACCEPTED).body("recruiters");
    }

    @GetMapping("/recruiters")
    public ResponseEntity<List<RecruiterFullDto>> getRecruiters() {
        RecruitersRepositoryImpl recruitersRepository = new RecruitersRepositoryImpl();
        ResponseEntity<List<RecruiterFullDto>> responseEntity = new ResponseEntity("", HttpStatus.NO_CONTENT);
        recruitersRepository.getRecruiters();
        responseEntity.status(HttpStatus.ACCEPTED).body(recruitersRepository);
        return responseEntity;
    }
}
