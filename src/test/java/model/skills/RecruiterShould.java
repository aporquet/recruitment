package model.skills;

import common.SkillsDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RecruiterShould {
    private Recruiter recruiter;
    private Candidate candidate;
    private SkillsDto recruiterSkills;
    private SkillsDto candidateSkills;
    private List<String> recruiterKeySkills;
    private List<String> candidateKeySkills;

    @Before
    public void init() {
        recruiterKeySkills = new ArrayList<>();
        this.recruiterSkills = new SkillsDto(recruiterKeySkills);
        recruiter = new Recruiter(recruiterSkills, 10);

        candidateKeySkills = new ArrayList<>();
        this.candidateSkills = new SkillsDto(candidateKeySkills);
        candidate = new Candidate(candidateSkills, 3);
    }

    @Test
    public void has_same_key_skills_in_common_with_candidate_to_test_him() {
        //Given
        recruiterKeySkills.add("React");
        recruiterKeySkills.add("Java");
        recruiterKeySkills.add("DevOps");
        recruiterKeySkills.add("HTML5");

        candidateKeySkills.add("HTML5");
        candidateKeySkills.add("Java");
        candidateKeySkills.add("DevOps");

        //When
        boolean recruiterCanTestCandidate = recruiter.canTest(candidate);

        //Then
        Assert.assertTrue(recruiterCanTestCandidate);
    }

    @Test
    public void not_be_able_to_test_candidate_if_recruiter_has_not_all_key_skills_in_common() {
        //Given
        recruiterKeySkills.add("React");
        recruiterKeySkills.add("Java");
        recruiterKeySkills.add("HTML5");

        candidateKeySkills.add("Java");
        candidateKeySkills.add("DevOps");

        //When
        boolean recruiterCanTestCandidate = recruiter.canTest(candidate);

        //Then
        Assert.assertFalse(recruiterCanTestCandidate);
    }

    @Test
    public void be_more_experienced_than_the_candidate(){
        //When
        boolean recruiterCanTestCandidate = recruiter.canTest(candidate);

        //Then
        Assert.assertTrue(recruiterCanTestCandidate);
    }

    @Test
    public void not_be_able_to_test_the_candidate_if_he_has_less_experienced_years_than_the_candidate(){
        //Given
        Recruiter recruiter = new Recruiter(recruiterSkills, 1);

        //When
        boolean recruiterCanTestCandidate = recruiter.canTest(candidate);

        //Then
        Assert.assertFalse(recruiterCanTestCandidate);
    }

}
