package use_case;

import common.RecruiterDto;
import common.RecruiterFullDto;

import java.util.List;

public interface RecruitersRepository {
    List<RecruiterFullDto> getRecruiters();

    RecruiterFullDto getRecruiter(String id);

    List<RecruiterDto> getRecruitersForSchedule();
}
