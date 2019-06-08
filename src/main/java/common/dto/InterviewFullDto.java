package common.dto;

public class InterviewFullDto {

    private int idInterview;
    private RecruiterFullDto recruiterFullDto;
    private CandidateFullDto candidateFullDto;

    public InterviewFullDto(int idInterview, RecruiterFullDto recruiterFullDto, CandidateFullDto candidateFullDto){
        this.idInterview = idInterview;
        this.recruiterFullDto = recruiterFullDto;
        this.candidateFullDto = candidateFullDto;
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
}
