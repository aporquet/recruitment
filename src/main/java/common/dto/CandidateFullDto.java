package common.dto;

import java.util.List;
import java.util.UUID;

public class CandidateFullDto {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String mail;
    private int experience;
    private String enterprise;
    private List<SkillFullDto> skills;
    private List<SkillFullDto> keySkills;

    public CandidateFullDto(UUID uuid, String firstName, String lastName, String mail, int experience, String enterprise, List<SkillFullDto> skills, List<SkillFullDto> keySkills){
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.experience = experience;
        this.enterprise = enterprise;
        this.skills = skills;
        this.keySkills = keySkills;
    }

    public List<SkillFullDto> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillFullDto> skills) {
        this.skills = skills;
    }

    public void setKeySkills(List<SkillFullDto> keySkills) {
        this.keySkills = keySkills;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid){ this.uuid = uuid; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public int getExperience() {
        return experience;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public List<SkillFullDto> getKeySkills() {
        return keySkills;
    }
}
