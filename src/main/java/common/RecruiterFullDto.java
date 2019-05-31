package common;

import java.util.UUID;

public class RecruiterFullDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String mail;

    public RecruiterFullDto(UUID id, String firstName, String lastName, String mail) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
    }

}
