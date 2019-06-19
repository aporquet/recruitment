package com.recruitment.exposition.query;

import common.dto.CandidateFullDto;
import common.dto.SkillFullDto;
import infra.mysql.CandidateRepositoryImpl;
import infra.mysql.SkillRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/skills")
public class SkillController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<SkillFullDto> getSkills() {
        SkillRepositoryImpl skillRepository = new SkillRepositoryImpl();
        return skillRepository.getSkills();
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody boolean insertSkill(@RequestBody String skill) {
        SkillRepositoryImpl skillRepository = new SkillRepositoryImpl();
        return skillRepository.insertSkill(skill);
    }

}
