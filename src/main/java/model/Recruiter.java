package model;

import java.util.ArrayList;
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

    public static Recruiter getTheRecruiterWithMostSkillsMatching(Candidate candidate, List<Recruiter> recruiters){
        int topCountMatchSkills = 0;
        List<String> skills = new ArrayList<String>();
        Recruiter recruiterMostAppropriate = new Recruiter(skills);
        for (Recruiter recruiter: recruiters) {
            int countMatchSkills = 0;
            for (String skill : candidate.getSkills()) {
                if (recruiter.skills.contains(skill)) {
                    countMatchSkills ++;
                }
            }
            if (countMatchSkills > topCountMatchSkills){
                topCountMatchSkills = countMatchSkills;
                recruiterMostAppropriate = recruiter;
            }
        }
        return recruiterMostAppropriate;
    }


}
