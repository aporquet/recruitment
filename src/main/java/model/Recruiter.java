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
}
