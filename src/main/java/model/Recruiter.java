package model;

import java.util.List;

public class Recruiter {

    private Skills skills;

    public Recruiter(Skills skills) {
        this.skills = skills;
    }

    public boolean canTest(Candidate candidate) {
        return skills.getKeySkills().containsAll(candidate.getKeySkills());
    }

    /*
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
            if (recruiter.canTest(candidate)) {
                int countMatchSkills = 0;
                for (String skill : candidate.getSkills()) {
                    if (recruiter.skills.contains(skill)) {
                        countMatchSkills++;
                    }
                }
                if (countMatchSkills > topCountMatchSkills) {
                    topCountMatchSkills = countMatchSkills;
                    recruiterMostAppropriate = recruiter;
                }
            }
        }
        return recruiterMostAppropriate;
    }*/

}
