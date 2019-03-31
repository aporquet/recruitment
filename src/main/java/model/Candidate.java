package model;

import java.util.List;

class Candidate {

    private Skills candidateSkills;
    private int experienceYears;

    Candidate(Skills skillsCandidate, int experienceYears) {
        this.candidateSkills = skillsCandidate;
        this.experienceYears = experienceYears;
    }

    List<String> getKeySkills() {
        return candidateSkills.getKeySkills();
    }

    int getExperienceYears() {
        return experienceYears;
    }

}
