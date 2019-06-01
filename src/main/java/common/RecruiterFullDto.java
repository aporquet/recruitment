package common;

import java.util.UUID;

public class RecruiterFullDto {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String mail;
    private String enterprise;

    public RecruiterFullDto(UUID uuid, String firstName, String lastName, String mail, String enterprise) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.enterprise = enterprise;
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
}
