package model;

import com.sun.java.swing.ui.OkCancelDialog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RecruiterShould {
    private Recruiter recruiter;
    private Candidate candidate;
    private Skills recruiterSkills;
    private Skills candidateSkills;
    private List<String> recruiterKeySkills;
    private List<String> candidateKeySkills;

    @Before
    public void init() {
        recruiterKeySkills = new ArrayList<String>();
        this.recruiterSkills = new Skills(recruiterKeySkills);
        recruiter = new Recruiter(recruiterSkills);

        candidateKeySkills = new ArrayList<String>();
        this.candidateSkills = new Skills(candidateKeySkills);
        candidate = new Candidate(candidateSkills);
    }

    @Test
    public void has_same_key_skills_in_common_with_candidate_to_test_him() {
        //Given
        recruiterKeySkills.add("React");
        recruiterKeySkills.add("Java");
        recruiterKeySkills.add("Devops");
        recruiterKeySkills.add("HTML5");

        candidateKeySkills.add("HTML5");
        candidateKeySkills.add("Java");
        candidateKeySkills.add("Devops");

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
        candidateKeySkills.add("Devops");

        //When
        boolean recruiterCanTestCandidate = recruiter.canTest(candidate);

        //Then
        Assert.assertFalse(recruiterCanTestCandidate);
    }

    /*
    @Test
    public void not_be_able_to_test_candidate_if_another_recruiter_also_can_test_candidate() {
        //Given
        recruiterSkills.add("React");
        recruiterSkills.add("HTML5");
        recruiterSkills.add("Java");
        Recruiter recruiter1 = new Recruiter(recruiterSkills);
        candidateSkills.add(".Net");
        candidateSkills.add("HTML5");
        candidateSkills.add("VueJs");
        Candidate candidate = new Candidate(candidateSkills);

        recruiterSkills.add("VueJs");
        Recruiter recruiter2 = new Recruiter(recruiterSkills);

        List<Recruiter> recruiters = new ArrayList<Recruiter>();
        recruiters.add(recruiter2);

        //When
        boolean recruiterIsTheOnlyPotentialCandidate = recruiter1.isTheOnlyRecruiterWithAvailableSkills(candidate, recruiters);

        //Then
        Assert.assertFalse(recruiterIsTheOnlyPotentialCandidate);
    }

    @Test
    public void not_be_able_to_test_candidate_if_another_recruiter_has_more_skills_in_common() {
        //Given
        recruiterSkills.add("React");
        recruiterSkills.add("HTML5");
        recruiterSkills.add("Java");
        Recruiter recruiter1 = new Recruiter(recruiterSkills);
        candidateSkills.add(".Net");
        candidateSkills.add("HTML5");
        candidateSkills.add("VueJs");
        Candidate candidate = new Candidate(candidateSkills);

        recruiterSkills.add("VueJs");
        Recruiter recruiter2 = new Recruiter(recruiterSkills);

        recruiterSkills.add("F#");
        Recruiter recruiter3 = new Recruiter(recruiterSkills);

        List<Recruiter> recruiters = new ArrayList<Recruiter>();
        recruiters.add(recruiter1);
        recruiters.add(recruiter2);
        recruiters.add(recruiter3);

        //When
        Recruiter recruiterTest = recruiter1.getTheRecruiterWithMostSkillsMatching(candidate, recruiters);

        //Then
        Assert.assertNotEquals(recruiterTest, recruiter1);

    }*/

/*    @Test
    public void has_a_general_level_superior_than_the_candidate() {

    }*/

}
