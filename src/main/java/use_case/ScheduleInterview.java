package use_case;

import common.RecruiterDto;
import model.availability.AvailableRecruiter;
import model.skills.SkillsChecker;

import java.util.UUID;

public class ScheduleInterview {

    private CandidateRepository candidateRepository;
    private RecruitersRepository recruitersRepository;
    private SkillsChecker skills;
    private AvailableRecruiter availability;
    private RecruiterDto firstAvailableRecruiter;

    public ScheduleInterview(CandidateRepository candidateRepository, RecruitersRepository recruitersRepository, SkillsChecker skills, AvailableRecruiter availability){
        this.candidateRepository = candidateRepository;
        this.recruitersRepository = recruitersRepository;
        this.skills = skills;
        this.availability = availability;
    }

    public void schedule(UUID candidateId) {
        candidateRepository.getCandidateById(candidateId);
        recruitersRepository.getRecruiters();
        skills.getTechnicallyCompetentRecruitersSortByOtherSkills();
        firstAvailableRecruiter = availability.getFirstAvailableRecruiter();
    }

    public RecruiterDto getFirstAvailableRecruiter() {
        return firstAvailableRecruiter;
    }
}
