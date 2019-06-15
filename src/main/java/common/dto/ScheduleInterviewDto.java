package common.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ScheduleInterviewDto {

    private UUID uuidCandidate;
    private LocalDateTime dateInterview;

    public UUID getUuidCandidate() {
        return uuidCandidate;
    }

    public LocalDateTime getDateInterview() {
        return dateInterview;
    }
}
