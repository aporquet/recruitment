package use_case;

import common.dto.InterviewDto;
import common.dto.InterviewFullDto;

import java.util.List;

public interface InterviewRespository {

    void save(InterviewDto interviewDto);

    void delete(int idInterview);

    List<InterviewFullDto> getInterviews();
}

