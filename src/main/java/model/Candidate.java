package model;

import java.util.List;

public class Candidate {

    private Skills candidateSkills;
    private int experienceYears;

    public Candidate(Skills skillsCandidate, int experienceYears) {
        this.candidateSkills = skillsCandidate;
        this.experienceYears = experienceYears;
    }

    public List<String> getKeySkills() {
        return candidateSkills.getKeySkills();

    }

    public int getExperienceYears() {
        return experienceYears;
    }

}
