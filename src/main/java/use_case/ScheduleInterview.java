package use_case;

import common.InterviewDto;
import infra.CandidateRepositoryImpl;
import infra.InterviewRepositoryImpl;
import infra.RecruitersRepositoryImpl;

import java.time.LocalDateTime;
import java.util.UUID;

public class ScheduleInterview {

    private CandidateRepositoryImpl candidateRepository;
    private RecruitersRepositoryImpl recruitersRepository;
    private InterviewRepositoryImpl interviewRespository;
    private LocalDateTime date;
    private UUID candidateId;

    public ScheduleInterview(CandidateRepositoryImpl candidateRepository, RecruitersRepositoryImpl recruitersRepository, InterviewRepositoryImpl interviewRespository, LocalDateTime date, UUID candidateId){
        this.candidateRepository = candidateRepository;
        this.recruitersRepository = recruitersRepository;
        this.interviewRespository = interviewRespository;
        this.date = date;
        this.candidateId = candidateId;
    }

    public void schedule(UUID candidateId) {
        candidateRepository.getCandidateById(candidateId);
        recruitersRepository.getRecruiters();
        interviewRespository.save(new InterviewDto());
    }

}
