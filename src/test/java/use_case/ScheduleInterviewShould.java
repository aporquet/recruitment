package use_case;

import common.dto.InterviewDto;
import infra.mysql.CandidateRepositoryImpl;
import infra.mysql.InterviewRepositoryImpl;
import infra.mysql.RecruitersRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.time.LocalDateTime;
import java.util.UUID;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ScheduleInterviewShould {

    private CandidateRepositoryImpl candidateRepository;
    private RecruitersRepositoryImpl recruitersRepository;
    private InterviewRepositoryImpl interviewRespository;
    private UUID uuidCandidate;
    private ScheduleInterview interview;
    private LocalDateTime date;

    @Before
    public void init() {
        candidateRepository = mock(CandidateRepositoryImpl.class);
        recruitersRepository = mock(RecruitersRepositoryImpl.class);
        interviewRespository = mock(InterviewRepositoryImpl.class);
        uuidCandidate = UUID.randomUUID();
        date = LocalDateTime.now();
        interview = new ScheduleInterview(candidateRepository, recruitersRepository, interviewRespository, date, uuidCandidate);
    }

/*    @Test
    public void call_candiadte_repository (){
        interview.schedule();
        verify(candidateRepository).getCandidateForSchedule(uuidCandidate);
    }

    @Test
    public void call_recruiters_repository (){
        interview.schedule();
        verify(recruitersRepository).getRecruitersForSchedule();
    }*/

/*    @Test
    public void save_new_interview(){
        verify(interviewRespository).save(Mockito.any(InterviewDto.class));
    }*/

}
