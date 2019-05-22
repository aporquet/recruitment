package common;

public class CandidateDto {

    private int idCandidate;
    private SkillsDto candidateSkills;
    private int experienceYears;

    public void setSkills(SkillsDto candidateSkills) {
        this.candidateSkills = candidateSkills;
    }

    public SkillsDto getCandidateSkills() {
        return candidateSkills;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public int getIdCandidate() {
        return this.idCandidate;
    }
}
