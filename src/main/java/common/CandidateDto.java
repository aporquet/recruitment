package common;

import java.util.UUID;

public class CandidateDto {

    private int idCandidate;
    private UUID uuidCandidate;
    private SkillsDto candidateSkills;
    private int experienceYears;

    public CandidateDto(int idCandidate, SkillsDto candidateSkills, int experienceYears){
        this.idCandidate = idCandidate;
        this.candidateSkills = candidateSkills;
        this.experienceYears = experienceYears;
    }

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
