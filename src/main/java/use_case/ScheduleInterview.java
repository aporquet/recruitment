package use_case;

import common.InterviewDto;
import common.RecruiterDto;
import model.availability.AvailableRecruiter;
import model.skills.SkillsChecker;

import java.util.UUID;

public class ScheduleInterview {

    private CandidateRepository candidateRepository;
    private RecruitersRepository recruitersRepository;
    private SkillsChecker skills;
    private AvailableRecruiter availability;
    private InterviewRespository interviewRespository;
    private RecruiterDto firstAvailableRecruiter;

    public ScheduleInterview(CandidateRepository candidateRepository, RecruitersRepository recruitersRepository, SkillsChecker skills, AvailableRecruiter availability, InterviewRespository interviewRespository){
        this.candidateRepository = candidateRepository;
        this.recruitersRepository = recruitersRepository;
        this.skills = skills;
        this.availability = availability;
        this.interviewRespository = interviewRespository;
    }

    public void schedule(UUID candidateId) {
        candidateRepository.getCandidateById(candidateId);
        recruitersRepository.getRecruiters();
        skills.getTechnicallyCompetentRecruitersSortByOtherSkills();
        firstAvailableRecruiter = availability.getFirstAvailableRecruiter();
        interviewRespository.save(new InterviewDto());
    }

}
