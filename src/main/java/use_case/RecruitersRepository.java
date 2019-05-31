package use_case;

import common.RecruiterDto;
import common.RecruiterFullDto;

import java.util.List;

public interface RecruitersRepository {
    List<RecruiterFullDto> getRecruiters();

    List<RecruiterDto> getRecruitersForSchedule();
}
