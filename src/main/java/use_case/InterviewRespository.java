package use_case;

import common.dto.InterviewDto;
import common.dto.InterviewFullDto;

import java.util.List;

public interface InterviewRespository {

    void save(InterviewDto interviewDto);

    List<InterviewFullDto> getInterviews();

    boolean deleteInterview(int idInterview);
}

