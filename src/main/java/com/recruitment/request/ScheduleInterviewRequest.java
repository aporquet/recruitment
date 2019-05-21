package com.recruitment.request;

import org.springframework.http.RequestEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class  ScheduleInterviewRequest implements BaseRequest{

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

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setIdCandidat(UUID idCandidat) {
        this.idCandidat = idCandidat;
    }

    @Override
    public boolean isValid() {
        if ((this.idCandidat) == UUID.fromString( "00000000-0000-0000-0000-000000000000" ) || (date) == null){
            return false;
        }
        return true;
    }
}
