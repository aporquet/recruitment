package infra.mySQL;

import com.mysql.cj.protocol.Resultset;
import common.RecruiterDto;
import org.springframework.core.SpringProperties;
import use_case.RecruitersRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RecruitersRepositoryImpl implements RecruitersRepository {

    @Override
    public List<RecruiterDto> getRecruiters() {
        List<RecruiterDto> recruiters = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        String url = SpringProperties.getProperty("jdbc.url");
        String username = SpringProperties.getProperty("jdbc.username");
        String password = SpringProperties.getProperty("jdbc.password");
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String getRecruiters = "SELECT idRecruiter, firstName, lastName, mail, e.Name FROM Person INNER JOIN Enterprise e ON idEnterprise = e.idEnterprise";
        try {
            ResultSet resultset = statement.executeQuery(getRecruiters);
            while (resultset.next()){
                String uuid = resultset.getString("idPerson");
                UUID id = UUID.fromString(uuid);
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                String mail = resultset.getString("mail");
                RecruiterDto recruiterDto = new RecruiterDto(id, firstName, lastName, mail);
                recruiters.add(recruiterDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recruiters;
    }

}
