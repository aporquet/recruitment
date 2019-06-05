package infra.mysql;

import common.InterviewDto;
import use_case.InterviewRespository;

public class InterviewRepositoryImpl implements InterviewRespository {

    @Override
    public void save(InterviewDto interviewDto) {

    }

    @Override
    public String getInterviews() {
        return "Interviews";
    }

}
