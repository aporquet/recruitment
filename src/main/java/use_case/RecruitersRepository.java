package use_case;

import common.RecruiterDto;

import java.util.List;

public interface RecruitersRepository {
    List<RecruiterDto> getRecruiters();
}
