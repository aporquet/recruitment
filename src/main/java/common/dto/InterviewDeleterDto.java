package common.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class InterviewDeleterDto {

    private int idInterview;
    private LocalDateTime dateInterview;
    private final UUID uuidRecruiter;
    private final UUID uuidCandidate;

    public InterviewDeleterDto(int idInterview, LocalDateTime dateInterview, UUID uuidRecruiter, UUID uuidCandidate){
        this.idInterview = idInterview;
        this.dateInterview = dateInterview;
        this.uuidRecruiter = uuidRecruiter;
        this.uuidCandidate = uuidCandidate;
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

    public UUID getUuidCandidate() {
        return uuidCandidate;
    }
}
