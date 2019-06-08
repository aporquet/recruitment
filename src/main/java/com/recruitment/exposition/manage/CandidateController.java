package com.recruitment.exposition.manage;

import common.dto.CandidateDto;
import common.dto.CandidateFullDto;
import infra.mysql.CandidateRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/candidates")
public class CandidateController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<CandidateFullDto> getCandidates() {
        CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
        return candidateRepository.getCandidates();
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody CandidateFullDto getCandidate(@PathVariable UUID uuid) {
        CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
        return candidateRepository.getCandidate(uuid);
    }

    @GetMapping("/sch/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    CandidateDto getCandidateForSchedule(@PathVariable UUID uuid) {
        CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
        return candidateRepository.getCandidateForSchedule(uuid);
    }
}
