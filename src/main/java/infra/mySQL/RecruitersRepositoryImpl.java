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
    public Statement statement = null;

    void mysqlConnection(){
        Connection connection = null;

        try(InputStream inputStream = new FileInputStream("/Users/Soat-AP/IdeaProjects/recruitment/jdbc.properties")) {
            Properties prop = new Properties();
            prop.load(inputStream);
            String url = prop.getProperty("db.url");
            String username = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
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
    }

    @Override
    public List<RecruiterFullDto> getRecruiters() {
        mysqlConnection();
        List<RecruiterFullDto> recruiters = new ArrayList<>();
        String getRecruiters = "SELECT p.uuidPerson, p.firstName, p.lastName, p.mail, e.name FROM Person p INNER JOIN Enterprise e ON p.idEnterprise = e.idEnterprise";
        try {
            ResultSet resultset = statement.executeQuery(getRecruiters);
            while (resultset.next()){
                String uuidString = resultset.getString("uuidPerson");
                UUID uuid = UUID.fromString(uuidString);
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                String mail = resultset.getString("mail");
                String enterprise = resultset.getString("name");
                RecruiterFullDto recruiterDto = new RecruiterFullDto(uuid, firstName, lastName, mail, enterprise);
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

    @Override
    public RecruiterFullDto getRecruiter(String id) {
        mysqlConnection();
        String uuidString ;
        UUID uuid = null;
        String firstName = null;
        String lastName = null;
        String mail = null;
        String enterprise = null;

            String getRecruiter = "SELECT p.uuidPerson, p.firstName, p.lastName, p.mail, e.name FROM Person p INNER JOIN Enterprise e ON p.idEnterprise = e.idEnterprise WHERE p.idPerson = "+id;
            try {
                ResultSet resultset = statement.executeQuery(getRecruiter);
                if (resultset.next()) {
                    uuidString = resultset.getString("uuidPerson");
                    uuid = UUID.fromString(uuidString);
                    firstName = resultset.getString("firstName");
                    lastName = resultset.getString("lastName");
                    mail = resultset.getString("mail");
                    enterprise = resultset.getString("name");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        RecruiterFullDto recruiter = new RecruiterFullDto(uuid, firstName, lastName, mail, enterprise);
        return recruiter;

    }
}
