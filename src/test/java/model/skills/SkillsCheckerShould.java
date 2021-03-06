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

        List<String> otherSkills = new ArrayList<>();
        otherSkills.add("DotNet");
        otherSkills.add("DevOps");

        SkillsDto candidateSkills = new SkillsDto();
        candidateSkills.setKeySkills(keySkills);
        candidateSkills.setOtherSkills(otherSkills);
        CandidateDto candidate = new CandidateDto();
        candidate.setSkills(candidateSkills);
        candidate.setExperienceYears(3);

        List<RecruiterDto> recruiters = new ArrayList<>();
        RecruiterDto firstRecruiter = new RecruiterDto();
        List<String> recruiterKeySkills = new ArrayList<>();
        recruiterKeySkills.add("Java");

        List<String> recruiterOtherSkills = new ArrayList<>();
        recruiterOtherSkills.add("Js");
        recruiterOtherSkills.add("CSS");

        SkillsDto skillsDto = new SkillsDto();
        skillsDto.setKeySkills(recruiterKeySkills);
        skillsDto.setOtherSkills(recruiterOtherSkills);
        firstRecruiter.setRecruiterSkills(skillsDto);
        firstRecruiter.setExperienceYears(5);
        recruiters.add(firstRecruiter);

        SkillsChecker skills = new SkillsChecker(candidate, recruiters);

        //When
        List<RecruiterDto> competentRecruiters = skills.getTechnicallyCompetentRecruitersSortByOtherSkills();

        //Then
        List<RecruiterDto> result = new ArrayList<>();
        result.add(firstRecruiter);
        Assert.assertEquals(competentRecruiters, result);
    }

    @Test
    public void not_return_recruiters_if_their_key_skills_not_matching_with_candidate_key_skills(){
        //Given
        List<String> keySkills = new ArrayList<>();
        keySkills.add("VueJs");

        List<String> otherSkills = new ArrayList<>();
        otherSkills.add("DotNet");
        otherSkills.add("DevOps");

        SkillsDto candidateSkills = new SkillsDto();
        candidateSkills.setKeySkills(keySkills);
        candidateSkills.setOtherSkills(otherSkills);
        CandidateDto candidate = new CandidateDto();
        candidate.setSkills(candidateSkills);
        candidate.setExperienceYears(3);

        List<RecruiterDto> recruiters = new ArrayList<>();
        RecruiterDto firstRecruiter = new RecruiterDto();
        List<String> recruiterKeySkills = new ArrayList<>();
        recruiterKeySkills.add("Java");
        recruiterKeySkills.add("Ruby");

        List<String> recruiterOtherSkills = new ArrayList<>();
        recruiterOtherSkills.add("Js");
        recruiterOtherSkills.add("VueJs");

        SkillsDto skillsDto = new SkillsDto();
        skillsDto.setKeySkills(recruiterKeySkills);
        skillsDto.setOtherSkills(recruiterOtherSkills);
        firstRecruiter.setRecruiterSkills(skillsDto);
        firstRecruiter.setExperienceYears(5);
        recruiters.add(firstRecruiter);

        SkillsChecker skills = new SkillsChecker(candidate, recruiters);

        //When
        List<RecruiterDto> competentRecruiters = skills.getTechnicallyCompetentRecruitersSortByOtherSkills();

        //Then
        List<RecruiterDto> result = new ArrayList<>();
        result.add(firstRecruiter);
        Assert.assertNotEquals(competentRecruiters, result);
    }

    @Test
    public void not_return_recruiters_if_candidate_experience_years_are_superiors(){
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
        candidate.setExperienceYears(12);

        List<RecruiterDto> recruiters = new ArrayList<>();
        RecruiterDto firstRecruiter = new RecruiterDto();
        RecruiterDto secondRecruiter = new RecruiterDto();
        RecruiterDto thirdRecruiter = new RecruiterDto();

        List<String> firstRecruiterKeySkills = new ArrayList<>();
        firstRecruiterKeySkills.add("Java");

        List<String> firstRecruiterOtherSkills = new ArrayList<>();
        firstRecruiterOtherSkills.add("BigData");
        firstRecruiterOtherSkills.add("Cloud");

        List<String> secondRecruiterKeySkills = new ArrayList<>();
        secondRecruiterKeySkills.add("Java");

        List<String> secondRecruiterOtherSkills = new ArrayList<>();
        secondRecruiterOtherSkills.add("DevOps");
        secondRecruiterOtherSkills.add("DotNet");

        List<String> thirdRecruiterKeySkills = new ArrayList<>();
        thirdRecruiterKeySkills.add("Java");

        List<String> thirdRecruiterOtherSkills = new ArrayList<>();
        thirdRecruiterOtherSkills.add("DotNet");
        thirdRecruiterOtherSkills.add("HTML5");

        SkillsDto firstRecruiterSkillsDto = new SkillsDto();
        firstRecruiterSkillsDto.setKeySkills(firstRecruiterKeySkills);
        firstRecruiterSkillsDto.setOtherSkills(firstRecruiterOtherSkills);
        firstRecruiter.setRecruiterSkills(firstRecruiterSkillsDto);
        firstRecruiter.setExperienceYears(3);

        SkillsDto secondRecruiterSkillsDto = new SkillsDto();
        secondRecruiterSkillsDto.setKeySkills(secondRecruiterKeySkills);
        secondRecruiterSkillsDto.setOtherSkills(secondRecruiterOtherSkills);
        secondRecruiter.setRecruiterSkills(secondRecruiterSkillsDto);
        secondRecruiter.setExperienceYears(8);

        SkillsDto thirdRecruiterSkillsDto = new SkillsDto();
        thirdRecruiterSkillsDto.setKeySkills(thirdRecruiterKeySkills);
        thirdRecruiterSkillsDto.setOtherSkills(thirdRecruiterOtherSkills);
        thirdRecruiter.setRecruiterSkills(thirdRecruiterSkillsDto);
        thirdRecruiter.setExperienceYears(5);

        recruiters.add(firstRecruiter);
        recruiters.add(secondRecruiter);
        recruiters.add(thirdRecruiter);
        SkillsChecker skills = new SkillsChecker(candidate, recruiters);

        //When
        List<RecruiterDto> result = skills.getTechnicallyCompetentRecruitersSortByOtherSkills();

        //Then
        List<RecruiterDto> expected = new ArrayList<>();
        expected.add(secondRecruiter);
        expected.add(thirdRecruiter);
        expected.add(firstRecruiter);
        Assert.assertNotEquals(expected, result);
        Assert.assertEquals(result.size(), 0);
    }

    // Add test key skill not matching

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
        candidate.setExperienceYears(1);

        List<RecruiterDto> recruiters = new ArrayList<>();
        RecruiterDto firstRecruiter = new RecruiterDto();
        RecruiterDto secondRecruiter = new RecruiterDto();
        RecruiterDto thirdRecruiter = new RecruiterDto();

        List<String> firstRecruiterKeySkills = new ArrayList<>();
        firstRecruiterKeySkills.add("Java");

        List<String> firstRecruiterOtherSkills = new ArrayList<>();
        firstRecruiterOtherSkills.add("BigData");
        firstRecruiterOtherSkills.add("Cloud");

        List<String> secondRecruiterKeySkills = new ArrayList<>();
        secondRecruiterKeySkills.add("Java");

        List<String> secondRecruiterOtherSkills = new ArrayList<>();
        secondRecruiterOtherSkills.add("DevOps");
        secondRecruiterOtherSkills.add("DotNet");

        List<String> thirdRecruiterKeySkills = new ArrayList<>();
        thirdRecruiterKeySkills.add("Java");

        List<String> thirdRecruiterOtherSkills = new ArrayList<>();
        thirdRecruiterOtherSkills.add("DotNet");
        thirdRecruiterOtherSkills.add("HTML5");

        SkillsDto firstRecruiterSkillsDto = new SkillsDto();
        firstRecruiterSkillsDto.setKeySkills(firstRecruiterKeySkills);
        firstRecruiterSkillsDto.setOtherSkills(firstRecruiterOtherSkills);
        firstRecruiter.setRecruiterSkills(firstRecruiterSkillsDto);
        firstRecruiter.setExperienceYears(10);

        SkillsDto secondRecruiterSkillsDto = new SkillsDto();
        secondRecruiterSkillsDto.setKeySkills(secondRecruiterKeySkills);
        secondRecruiterSkillsDto.setOtherSkills(secondRecruiterOtherSkills);
        secondRecruiter.setRecruiterSkills(secondRecruiterSkillsDto);
        secondRecruiter.setExperienceYears(5);

        SkillsDto thirdRecruiterSkillsDto = new SkillsDto();
        thirdRecruiterSkillsDto.setKeySkills(thirdRecruiterKeySkills);
        thirdRecruiterSkillsDto.setOtherSkills(thirdRecruiterOtherSkills);
        thirdRecruiter.setRecruiterSkills(thirdRecruiterSkillsDto);
        thirdRecruiter.setExperienceYears(7);

        recruiters.add(firstRecruiter);
        recruiters.add(secondRecruiter);
        recruiters.add(thirdRecruiter);
        SkillsChecker skills = new SkillsChecker(candidate, recruiters);

        //When
        List<RecruiterDto> result = skills.getTechnicallyCompetentRecruitersSortByOtherSkills();

        //Then
        List<RecruiterDto> expected = new ArrayList<>();
        expected.add(secondRecruiter);
        expected.add(thirdRecruiter);
        expected.add(firstRecruiter);
        Assert.assertEquals(expected, result);
    }
}

