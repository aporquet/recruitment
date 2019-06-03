package common;

import java.time.LocalDateTime;
import java.util.List;

public class RecruiterDto {

    private int id;
    private LocalDateTime availableDate;
    private List<LocalDateTime> recruiterAvailabilities;
    private SkillsDto recruiterSkills;
    private int experienceYears;

    public RecruiterDto(int id, List<LocalDateTime> recruiterAvailabilities, SkillsDto recruiterSkills, int experienceYears){
        this.id=id;
        this.recruiterAvailabilities = recruiterAvailabilities;
        this.recruiterSkills = recruiterSkills;
        this.experienceYears = experienceYears;
    }

    public void setAvailability(LocalDateTime availableDate) {
        this.availableDate = availableDate;
    }

    public LocalDateTime getAvailableDate() {
        return availableDate;
    }

    public List<LocalDateTime> availabilities() {
        return recruiterAvailabilities;
    }

    public void setAvailabilities(List<LocalDateTime> recruiterAvailabilities) {

        this.recruiterAvailabilities = recruiterAvailabilities;
    }

    public List<LocalDateTime> getRecruiterAvailabilities() {
        return recruiterAvailabilities;
    }

    public SkillsDto getRecruiterSkills() {
        return recruiterSkills;
    }

    public void setRecruiterSkills(SkillsDto recruiterSkills) {
        this.recruiterSkills = recruiterSkills;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public int getId() {
        return id;
    }
}
