package common.dto;

import java.time.LocalDateTime;

public class InterviewFullDto {

    private int idInterview;
    private RecruiterFullDto recruiterFullDto;
    private CandidateFullDto candidateFullDto;
    private LocalDateTime localDateTime;

    public InterviewFullDto(int idInterview, RecruiterFullDto recruiterFullDto, CandidateFullDto candidateFullDto, LocalDateTime dateInterview){
        this.idInterview = idInterview;
        this.recruiterFullDto = recruiterFullDto;
        this.candidateFullDto = candidateFullDto;
        this.localDateTime = dateInterview;
    }

    public RecruiterFullDto getRecruiterFullDto() {
        return recruiterFullDto;
    }

    public void setRecruiterFullDto(RecruiterFullDto recruiterFullDto) {
        this.recruiterFullDto = recruiterFullDto;
    }

    public CandidateFullDto getCandidateFullDto() {
        return candidateFullDto;
    }

    public void setCandidateFullDto(CandidateFullDto candidateFullDto) {
        this.candidateFullDto = candidateFullDto;
    }

    public int getIdInterview() {
        return idInterview;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
