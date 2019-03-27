package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RecruiterShould {
    private List<String> recruiterSkills;
    private List<String> candidateSkills;

    @Before
    public void init(){
        this.recruiterSkills = new ArrayList<String>();
        this.candidateSkills = new ArrayList<String>();
    }

    @Test
    public void has_at_least_one_skill_in_common_with_candidate_to_test_him(){
        //Given
        recruiterSkills.add("React");
        recruiterSkills.add("HTML5");
        Recruiter recruiter = new Recruiter(recruiterSkills);
        candidateSkills.add("HTML5");
        candidateSkills.add("Java");
        candidateSkills.add("Devops");
        Candidate candidate = new Candidate(candidateSkills);

        //When
        boolean recruiterCanTestCandidate = recruiter.canTest(candidate);

        //Then
        Assert.assertTrue(recruiterCanTestCandidate);
    }

    @Test
    public void not_be_able_to_test_candidate_if_recruiter_has_any_skill_in_common(){
        //Given
        recruiterSkills.add("React");
        recruiterSkills.add("HTML5");
        Recruiter recruiter = new Recruiter(recruiterSkills);
        candidateSkills.add("Java");
        candidateSkills.add("Devops");
        Candidate candidate = new Candidate(candidateSkills);

        //When
        boolean recruiterCanTestCandidate = recruiter.canTest(candidate);

        //Then
        Assert.assertFalse(recruiterCanTestCandidate);
    }
}
