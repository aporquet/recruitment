package model.skills;

import common.CandidateDto;
import common.RecruiterDto;
import common.SkillsDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SkillsCheckerShould {

/*    @Test
    public void return_recruiters_who_are_technically_competent_to_evaluate_candidate(){
        //Given
        List<String> keySkills = new ArrayList<>();
        keySkills.add("Java");
        SkillsDto candidateSkills = new SkillsDto();
        candidateSkills.setKeySkills(keySkills);
        CandidateDto candidate = new CandidateDto();
        candidate.setSkills(candidateSkills);

        List<RecruiterDto> recruiters = new ArrayList<>();
        RecruiterDto firstRecruiter = new RecruiterDto();
        List<String> recruiterKeySkills = new ArrayList<>();
        recruiterKeySkills.add("Java");
        SkillsDto skillsDto = new SkillsDto();
        skillsDto.setKeySkills(recruiterKeySkills);
        firstRecruiter.setRecruiterSkills(skillsDto);
        recruiters.add(firstRecruiter);

        SkillsChecker skills = new SkillsChecker(candidate, recruiters);

        //When
        Set<Recruiter> competentRecruiters = skills.getTechnicallyCompetentRecruiters();

        //Then
        List<Recruiter> result = new ArrayList<>();
        result.add(firstRecruiter);
        Assert.assertEquals(competentRecruiters, result);
    }

    @Test
    public void return_competent_recruiters_sorted_by_their_other_skills(){
        //Given
        List<String> keySkills = new ArrayList<>();
        keySkills.add("Java");

        List<String> otherSkills = new ArrayList<>();
        otherSkills.add("DotNet");
        otherSkills.add("DevOps");

        SkillsDto candidateSkills = new SkillsDto();
        candidateSkills.setKeySkills(keySkills);
        candidateSkills.setOtherSkills(otherSkills);
        CandidateDto candidate = new CandidateDto();
        candidate.setSkills(candidateSkills);

        List<RecruiterDto> recruiters = new ArrayList<>();
        RecruiterDto firstRecruiter = new RecruiterDto();
        RecruiterDto secondRecruiter = new RecruiterDto();

        List<String> firstRecruiterKeySkills = new ArrayList<>();
        firstRecruiterKeySkills.add("Java");

        List<String> firstRecruiterOtherSkills = new ArrayList<>();
        firstRecruiterOtherSkills.add("BigData");
        firstRecruiterOtherSkills.add("Cloud");

        List<String> secondRecruiterKeySkills = new ArrayList<>();
        secondRecruiterKeySkills.add("Java");

        List<String> secondRecruiterOtherSkills = new ArrayList<>();
        secondRecruiterOtherSkills.add("Js");
        secondRecruiterOtherSkills.add("DotNet");

        SkillsDto firstRecruiterSkillsDto = new SkillsDto();
        firstRecruiterSkillsDto.setKeySkills(firstRecruiterKeySkills);
        firstRecruiterSkillsDto.setOtherSkills(firstRecruiterOtherSkills);
        firstRecruiter.setRecruiterSkills(firstRecruiterSkillsDto);

        SkillsDto secondRecruiterSkillsDto = new SkillsDto();
        secondRecruiterSkillsDto.setKeySkills(secondRecruiterKeySkills);
        secondRecruiterSkillsDto.setOtherSkills(secondRecruiterOtherSkills);
        secondRecruiter.setRecruiterSkills(secondRecruiterSkillsDto);

        recruiters.add(firstRecruiter);
        recruiters.add(secondRecruiter);
        SkillsChecker skills = new SkillsChecker(candidate, recruiters);

        //When
        Set<Recruiter> result = skills.getTechnicallyCompetentRecruiters();

        //Then
        List<RecruiterDto> expected = new ArrayList<>();
        expected.add(secondRecruiter);
        expected.add(firstRecruiter);
        Assert.assertEquals(expected, result);
    }*/

    //Todo : Add tests to verify all conditions
    // refactor .add to Arrayslist


}

