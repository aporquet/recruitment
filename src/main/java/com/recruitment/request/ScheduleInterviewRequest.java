package com.recruitment.request;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ScheduleInterviewRequest extends BaseRequest{

    private UUID idCandidat;
    private LocalDateTime date;

    public ScheduleInterviewRequest(UUID idCandidat, LocalDateTime date){
        this.idCandidat = idCandidat;
        this.date = date;
    }

    public UUID getIdCandidat() {
        return idCandidat;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public boolean isValid() {
        if ((idCandidat) == UUID.fromString( "00000000-0000-0000-0000-000000000000" ) || (date) == null){
            return false;
        }
        return true;
    }

}
