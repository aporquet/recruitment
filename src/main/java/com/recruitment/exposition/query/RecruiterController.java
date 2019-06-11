package com.recruitment.exposition.query;

import common.dto.RecruiterDto;
import common.dto.RecruiterFullDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import infra.mysql.RecruitersRepositoryImpl;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/recruiters")
public class RecruiterController {

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody RecruiterFullDto getRecruiter(@PathVariable UUID uuid) {
        RecruitersRepositoryImpl recruitersRepository = new RecruitersRepositoryImpl();
        return recruitersRepository.getRecruiter(uuid);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<RecruiterFullDto> getRecruiters() {
        RecruitersRepositoryImpl recruitersRepository = new RecruitersRepositoryImpl();
        return recruitersRepository.getRecruiters();
    }

    @GetMapping("/sch")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<RecruiterDto> getRecruitersForSchedule() {
        RecruitersRepositoryImpl recruitersRepository = new RecruitersRepositoryImpl();
        return recruitersRepository.getRecruitersForSchedule();
    }
}
