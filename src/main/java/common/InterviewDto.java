package common;

import java.time.LocalDateTime;

public class InterviewDto {

    private int idCandidate;
    private int idRecruiter;
    private LocalDateTime dateTime;

    public InterviewDto(int idCandidate, int idRecruiter, LocalDateTime dateTime){
        this.idCandidate = idCandidate;
        this.idRecruiter = idRecruiter;
    }
}
