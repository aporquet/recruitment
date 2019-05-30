package com.recruitment.exposition.query;

import common.RecruiterDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import infra.mySQL.RecruitersRepositoryImpl;

import java.util.List;

public class Recruiter {

    // TODO: Get query recruiters requests

    @GetMapping("/recruiters")
    public String getRecruiters() {
        return "Recruiters";
    }

    @GetMapping("/recruiters/id")
    public ResponseEntity<List<RecruiterDto>> getRecruiter() {
        RecruitersRepositoryImpl recruitersRepository = new RecruitersRepositoryImpl();
        ResponseEntity<List<RecruiterDto>> responseEntity = new ResponseEntity("", HttpStatus.NO_CONTENT);
        recruitersRepository.getRecruiters();
        responseEntity.status(HttpStatus.ACCEPTED).body(recruitersRepository);
        return responseEntity;
    }
}
