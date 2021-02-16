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
        for(String recruiterKeySkill : recruiterSkills.getKeySkills()){
            for(String candidateKeySkill : candidate.getKeySkills()){
                if (recruiterKeySkill.equals(candidateKeySkill)){
                    return true;
                }
            }
        }
        return false;
    }

    List<String> getOtherSkills() {
        return recruiterSkills.getOtherSkills();
    }
}
