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
        if(candidate.getExperienceYears() > experienceYears){
            return false;
        }
        if(recruiterSkills.getKeySkills().contains(candidate.getKeySkills())){
            return false;
        }
        return true;
    }

    List<String> getOtherSkills() {
        return recruiterSkills.getOtherSkills();
    }
}
