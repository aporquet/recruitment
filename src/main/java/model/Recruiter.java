package model;

import java.util.List;

public class Recruiter {

    private List<String> skills;

    public Recruiter(List<String> skills) {
        this.skills = skills;
    }

    public boolean canTest(Candidate candidate) {

        for (String skill: candidate.getSkills()) {
            if (this.skills.contains(skill)){
                return true;
            }
        }
        return false;
    }

    public boolean isTheOnlyRecruiterWithAvailableSkills(Candidate candidate, List<Recruiter> recruiters){

        for (Recruiter recruiter: recruiters){
            if (recruiter.canTest(candidate)){
                return false;
            }
        }
        return true;
    }

    public boolean isTheMostApropriate(Candidate candidate, List<Recruiter> recruiters){
        return true;
    }



}
