package com.recruitment.exposition.query;

import common.dto.CandidateDto;
import common.dto.CandidateFullDto;
import infra.mysql.CandidateRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/candidates", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST})
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

    @DeleteMapping("/delete/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteCandidate(@PathVariable UUID uuid) {
        CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
        return candidateRepository.deleteCandidate(uuid);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody boolean insertCandidate(@RequestBody CandidateFullDto candidate) {
        CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
        candidate = candidateRepository.generateUUID(candidate);
        return candidateRepository.insertCandidate(candidate);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody boolean updateCandidate(@RequestBody CandidateFullDto candidate) {
        CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
        return candidateRepository.updateCandidate(candidate);
    }
}
