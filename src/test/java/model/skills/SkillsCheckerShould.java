package model.skills;

import common.CandidateDto;
import common.RecruiterDto;
import common.SkillsDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SkillsCheckerShould {

    @Test
    public void return_recruiters_who_are_technically_competent_to_evaluate_candidate(){
        //Given
        List<String> keySkills = new ArrayList<>();
        keySkills.add("Java");
        SkillsDto candidateSkills = new SkillsDto(keySkills);
        CandidateDto candidate = new CandidateDto();
        candidate.setSkills(candidateSkills);

        List<RecruiterDto> recruiters = new ArrayList<>();
        RecruiterDto firstRecruiter = new RecruiterDto();
        List<String> recruiterKeySkills = new ArrayList<>();
        recruiterKeySkills.add("Java");
        SkillsDto skillsDto = new SkillsDto(recruiterKeySkills);
        firstRecruiter.setRecruiterSkills(skillsDto);
        recruiters.add(firstRecruiter);

        SkillsChecker skills = new SkillsChecker(candidate, recruiters);

        //When
        List<RecruiterDto> competentRecruiters = skills.getTechnicallyCompetentRecruiters();

        //Then
        List<RecruiterDto> result = new ArrayList<>();
        result.add(firstRecruiter);
        Assert.assertEquals(competentRecruiters, result);
    }

    //Todo : Add tests to verify all conditions

}
