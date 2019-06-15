package common.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class InterviewDeleterDto {

    private int idInterview;
    private LocalDateTime dateInterview;
    private final UUID uuidRecruiter;

    public InterviewDeleterDto(int idInterview, LocalDateTime dateInterview, UUID uuidRecruiter){
        this.idInterview = idInterview;
        this.dateInterview = dateInterview;
        this.uuidRecruiter = uuidRecruiter;
    }

    public int getIdInterview() {
        return idInterview;
    }

    public LocalDateTime getDateInterview() {
        return dateInterview;
    }

    public void setDateInterview(LocalDateTime dateInterview) {
        this.dateInterview = dateInterview;
    }

    public UUID getUuidRecruiter() {
        return uuidRecruiter;
    }
}
