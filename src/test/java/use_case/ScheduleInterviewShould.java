/*
package use_case;

import common.InterviewDto;
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
    private UUID idCandidate;
    private ScheduleInterview interview;
    private LocalDateTime date;

    @Before
    public void init() {
        candidateRepository = mock(CandidateRepositoryImpl.class);
        recruitersRepository = mock(RecruitersRepositoryImpl.class);
        interviewRespository = mock(InterviewRepositoryImpl.class);
        idCandidate = UUID.randomUUID();
        date = LocalDateTime.now();
        interview = new ScheduleInterview(candidateRepository, recruitersRepository, interviewRespository, date, idCandidate);
        interview.schedule();
    }

    @Test
    public void call_candiadte_repository (){
        verify(candidateRepository).getCandidateById(idCandidate);
    }

    @Test
    public void call_recruiters_repository (){
        verify(recruitersRepository).getRecruitersForSchedule();
    }

    @Test
    public void save_new_interview(){
        verify(interviewRespository).save(Mockito.any(InterviewDto.class));
    }

}
*/
