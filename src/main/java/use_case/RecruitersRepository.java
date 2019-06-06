package use_case;

import common.RecruiterDto;
import common.RecruiterFullDto;

import java.util.List;
import java.util.UUID;

public interface RecruitersRepository {
    List<RecruiterFullDto> getRecruiters();

    RecruiterFullDto getRecruiter(UUID uuid);

    List<RecruiterDto> getRecruitersForSchedule();
}
