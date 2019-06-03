package com.recruitment.exposition.query;

import common.RecruiterDto;
import common.RecruiterFullDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import infra.mySQL.RecruitersRepositoryImpl;

import java.util.*;

@RestController
@RequestMapping(value = "/recruiters")
public class Recruiter {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody RecruiterFullDto getRecruiter(@PathVariable String id) {
        RecruitersRepositoryImpl recruitersRepository = new RecruitersRepositoryImpl();
        return recruitersRepository.getRecruiter(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<RecruiterFullDto> getRecruiters() {
        RecruitersRepositoryImpl recruitersRepository = new RecruitersRepositoryImpl();
        return recruitersRepository.getRecruiters();
    }

    @GetMapping("/recruitersSchedulable")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<RecruiterDto> getRecruitersForSchedule() {
        RecruitersRepositoryImpl recruitersRepository = new RecruitersRepositoryImpl();
        return recruitersRepository.getRecruitersForSchedule();
    }
}
