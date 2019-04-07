package model.skills;

import common.SkillsDto;

import java.util.List;

class Candidate {

    private SkillsDto candidateSkills;
    private int experienceYears;

    Candidate(SkillsDto skillsCandidate, int experienceYears) {
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
