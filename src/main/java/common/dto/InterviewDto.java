package common.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class InterviewDto {

    private final UUID uuidCandidate;
    private final UUID uuidRecruiter;
    private LocalDateTime dateTime;

    public InterviewDto(UUID uuidCandidate, UUID uuidRecruiter, LocalDateTime dateTime){
        this.uuidCandidate = uuidCandidate;
        this.uuidRecruiter = uuidRecruiter;
        this.dateTime = dateTime;
    }

    public UUID getUuidCandidate() {
        return uuidCandidate;
    }

    public UUID getUuidRecruiter() {
        return uuidRecruiter;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
