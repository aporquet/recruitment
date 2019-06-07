package use_case;

import common.CandidateDto;
import common.InterviewDto;
import common.RecruiterDto;
import infra.mysql.CandidateRepositoryImpl;
import infra.mysql.InterviewRepositoryImpl;
import infra.mysql.RecruitersRepositoryImpl;
import model.skills.SkillsChecker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ScheduleInterview {

    private CandidateRepositoryImpl candidateRepository;
    private RecruitersRepositoryImpl recruitersRepository;
    private InterviewRepositoryImpl interviewRespository;
    private LocalDateTime date;
    private UUID candidateUuid;

    public ScheduleInterview(CandidateRepositoryImpl candidateRepository, RecruitersRepositoryImpl recruitersRepository, InterviewRepositoryImpl interviewRespository, LocalDateTime date, UUID candidateUuid){
        this.candidateRepository = candidateRepository;
        this.recruitersRepository = recruitersRepository;
        this.interviewRespository = interviewRespository;
        this.date = date;
        this.candidateUuid = candidateUuid;
    }

    public void schedule() {
        CandidateDto candidate = candidateRepository.getCandidateForSchedule(candidateUuid);
        List<RecruiterDto> recruiterDtoList = recruitersRepository.getRecruitersForSchedule();
        SkillsChecker skillsChecker = new SkillsChecker(candidate, recruiterDtoList);
        List<RecruiterDto> competentRecruiters = skillsChecker.getTechnicallyCompetentRecruitersSortByOtherSkills();
        InterviewDto interviewDto = new InterviewDto(candidate.getUuidCandidate(), competentRecruiters.get(0).getUuid(), this.date);
        interviewRespository.save(interviewDto);
    }

}
