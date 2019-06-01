package com.recruitment.exposition.query;

import common.AnyRecruiterFoundException;
import common.RecruiterFullDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import infra.mySQL.RecruitersRepositoryImpl;

import java.util.*;

@RestController
@RequestMapping(value = "/recruiters")
public class Recruiter {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public @ResponseBody RecruiterFullDto getRecruiter(@PathVariable String id) {
        RecruitersRepositoryImpl recruitersRepository = new RecruitersRepositoryImpl();
        return recruitersRepository.getRecruiter(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public @ResponseBody List<RecruiterFullDto> getRecruiters() {
        RecruitersRepositoryImpl recruitersRepository = new RecruitersRepositoryImpl();
        return recruitersRepository.getRecruiters();
    }

}
