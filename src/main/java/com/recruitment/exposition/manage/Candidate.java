package com.recruitment.exposition.manage;

import common.CandidateFullDto;
import infra.mySQL.CandidateRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/candidates")
public class Candidate {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<CandidateFullDto> getCandidates() {
        CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
        return candidateRepository.getCandidates();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody CandidateFullDto getCandidate(@PathVariable String id) {
        CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
        return candidateRepository.getCandidateById(id);
    }
}
