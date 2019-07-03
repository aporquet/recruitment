package use_case;

import common.dto.RecruiterDto;
import common.dto.RecruiterFullDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface RecruitersRepository {
    List<RecruiterFullDto> getRecruiters();

    RecruiterFullDto getRecruiter(UUID uuid);

    List<RecruiterDto> getRecruitersForSchedule();

    List<LocalDateTime> getRecruitersAvailabilities();
}
