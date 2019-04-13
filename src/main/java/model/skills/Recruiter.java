package model.skills;

import common.RecruiterDto;
import common.SkillsDto;

import java.util.List;

class Recruiter {

    private SkillsDto recruiterSkills;
    private int experienceYears;

    Recruiter(SkillsDto skills, int experienceYears) {
        this.recruiterSkills = skills;
        this.experienceYears = experienceYears;
    }

    boolean canTest(Candidate candidate) {
        return recruiterSkills.getKeySkills().containsAll(candidate.getKeySkills()) && experienceYears > candidate.getExperienceYears();
    }

    List<String> getOtherSkills() {
        return recruiterSkills.getOtherSkills();
    }
}
