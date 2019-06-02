package common;

import java.util.List;
import java.util.UUID;

public class RecruiterFullDto {

    private String id;
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String mail;
    private String experience;
    private String enterprise;
    private List<String> skills;

    public RecruiterFullDto(String id, UUID uuid, String firstName, String lastName,String experience, String mail, String enterprise, List<String> skills) {
        this.id = id;
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.experience = experience;
        this.enterprise = enterprise;
        this.skills = skills;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getId() {
        return id;
    }

    public String getExperience() {
        return experience;
    }
}
