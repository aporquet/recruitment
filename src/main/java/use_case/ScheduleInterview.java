package use_case;

import common.CandidateDto;
import common.InterviewDto;
import common.RecruiterDto;
import infra.mySQL.CandidateRepositoryImpl;
import infra.mySQL.InterviewRepositoryImpl;
import infra.mySQL.RecruitersRepositoryImpl;
import model.skills.SkillsChecker;

import java.time.LocalDateTime;
import java.util.List;
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

    public void schedule() {
        CandidateDto candidate = candidateRepository.getCandidateByUUIDForSchedule(candidateId);
        List<RecruiterDto> recruiterDtoList = recruitersRepository.getRecruitersForSchedule();
        SkillsChecker skillsChecker = new SkillsChecker(candidate, recruiterDtoList);
        List<RecruiterDto> competentRecruiters = skillsChecker.getTechnicallyCompetentRecruitersSortByOtherSkills();
        InterviewDto interviewDto = new InterviewDto(candidate.getIdCandidate(), competentRecruiters.get(0).getId(), this.date);
        interviewRespository.save(interviewDto);
    }

}
