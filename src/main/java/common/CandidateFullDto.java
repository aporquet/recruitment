package common;

import java.util.List;
import java.util.UUID;

public class CandidateFullDto {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String mail;
    private int experience;
    private String enterprise;
    private List<String> skills;
    private List<String> keySkills;

    public CandidateFullDto(UUID uuid, String firstName, String lastName, String mail, int experience, String enterprise, List<String> skills, List<String> keySkills){
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.experience = experience;
        this.enterprise = enterprise;
        this.skills = skills;
        this.keySkills = keySkills;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void setKeySkills(List<String> keySkills) {
        this.keySkills = keySkills;
    }

    public UUID getUuid() {
        return uuid;
    }

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

    public List<String> getKeySkills() {
        return keySkills;
    }
}
