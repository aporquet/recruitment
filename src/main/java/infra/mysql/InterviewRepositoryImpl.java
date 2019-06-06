package infra.mysql;

import common.InterviewDto;
import use_case.InterviewRespository;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InterviewRepositoryImpl implements InterviewRespository {

    public Statement statement = null;

    void mysqlConnection() {
        Connection connection = DbConnect.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(InterviewDto interviewDto) {

    }

    @Override
    public String getInterviews() {
        return "Interviews";
    }

}
