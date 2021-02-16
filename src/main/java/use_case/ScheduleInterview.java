package use_case;

import common.dto.CandidateDto;
import common.dto.InterviewDto;
import common.dto.RecruiterDto;
import infra.mysql.CandidateRepositoryImpl;
import infra.mysql.InterviewRepositoryImpl;
import infra.mysql.RecruitersRepositoryImpl;
import model.availability.AvailableRecruiter;
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
        AvailableRecruiter availableRecruiter = new AvailableRecruiter(competentRecruiters);
        RecruiterDto recruiterValidate = availableRecruiter.getAvailableRecruiter(this.date);
        InterviewDto interviewDto = new InterviewDto(candidate.getUuidCandidate(), recruiterValidate.getUuid(), this.date);
        interviewRespository.save(interviewDto);
    }

}
