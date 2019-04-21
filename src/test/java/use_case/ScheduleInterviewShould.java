package use_case;

import common.InterviewDto;
import common.RecruiterDto;
import model.availability.AvailableRecruiter;
import model.skills.SkillsChecker;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ScheduleInterviewShould {

    private CandidateRepository candidateRepository;
    private RecruitersRepository recruitersRepository;
    private UUID idCandidate;
    private SkillsChecker skills;
    private AvailableRecruiter availability;
    private ScheduleInterview interview;
    private InterviewRespository interviewRespository;

    @Before
    public void init() {
        candidateRepository = mock(CandidateRepository.class);
        recruitersRepository = mock(RecruitersRepository.class);
        interviewRespository = mock(InterviewRespository.class);
        availability = mock(AvailableRecruiter.class);
        skills = mock(SkillsChecker.class);
        idCandidate = UUID.randomUUID();
        interview = new ScheduleInterview(candidateRepository, recruitersRepository, skills, availability, interviewRespository);
        interview.schedule(idCandidate);
    }

    @Test
    public void call_candiadte_repository (){
        verify(candidateRepository).getCandidateById(idCandidate);
    }

    @Test
    public void call_recruiters_repository (){
        verify(recruitersRepository).getRecruiters();
    }

    @Test
    public void call_agregate_skills (){
        verify(skills).getTechnicallyCompetentRecruitersSortByOtherSkills();
    }

    @Test
    public void call_agregate_availability (){
       verify(availability).getFirstAvailableRecruiter();
    }

    @Test
    public void save_new_interview(){
        verify(interviewRespository).save(Mockito.any(InterviewDto.class));
    }

}
