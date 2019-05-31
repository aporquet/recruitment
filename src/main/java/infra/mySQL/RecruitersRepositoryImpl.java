package infra.mySQL;

import common.RecruiterDto;
import common.RecruiterFullDto;
import use_case.RecruitersRepository;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class RecruitersRepositoryImpl implements RecruitersRepository {

    @Override
    public List<RecruiterFullDto> getRecruiters() {
        List<RecruiterFullDto> recruiters = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = new FileInputStream("/Users/Soat-AP/IdeaProjects/recruitment/jdbc.properties")) {
            Properties prop = new Properties();
            prop.load(inputStream);
            String url = prop.getProperty("db.url");
            String username = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String getRecruiters = "SELECT uuidPerson, firstName, lastName, mail, e.Name FROM Person INNER JOIN Enterprise e ON idEnterprise = e.idEnterprise";
        try {
            ResultSet resultset = statement.executeQuery(getRecruiters);
            while (resultset.next()){
                String uuid = resultset.getString("uuidPerson");
                UUID id = UUID.fromString(uuid);
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                String mail = resultset.getString("mail");
                RecruiterFullDto recruiterDto = new RecruiterFullDto(id, firstName, lastName, mail);
                recruiters.add(recruiterDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recruiters;
    }

    @Override
    public List<RecruiterDto> getRecruitersForSchedule() {
        List<RecruiterDto> recruiters = new ArrayList<>();
        return recruiters;
    }

}
