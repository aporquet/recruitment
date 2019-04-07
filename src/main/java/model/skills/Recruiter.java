package model.skills;

import common.SkillsDto;

class Recruiter {

    private SkillsDto skills;
    private int experienceYears;

    Recruiter(SkillsDto skills, int experienceYears) {
        this.skills = skills;
        this.experienceYears = experienceYears;
    }

    boolean canTest(Candidate candidate) {
        return skills.getKeySkills().containsAll(candidate.getKeySkills()) && experienceYears > candidate.getExperienceYears();
    }

}
