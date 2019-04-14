package model.skills;

import java.util.List;

class Recruiter {

    private Skills recruiterSkills;
    private int experienceYears;

    Recruiter(Skills skills, int experienceYears) {
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
