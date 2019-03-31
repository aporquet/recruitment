package model.skills;

class Recruiter {

    private Skills skills;
    private int experienceYears;

    Recruiter(Skills skills, int experienceYears) {
        this.skills = skills;
        this.experienceYears = experienceYears;
    }

    boolean canTest(Candidate candidate) {
        return skills.getKeySkills().containsAll(candidate.getKeySkills()) && experienceYears > candidate.getExperienceYears();
    }

}
