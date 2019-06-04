package infra.mySQL;

import common.CandidateDto;
import common.CandidateFullDto;
import common.RecruiterFullDto;
import common.RecruiterNotFoundException;
import use_case.CandidateRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class CandidateRepositoryImpl implements CandidateRepository {
    public Statement statement = null;

    void mysqlConnection() {
        Connection connection = null;
        try (InputStream inputStream = new FileInputStream("/Users/Soat-AP/IdeaProjects/recruitment/jdbc.properties")) {
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
    public CandidateFullDto getCandidateById(String idCandidate) {
        mysqlConnection();
        String idPerson = null;
        String uuidString;
        UUID uuid = null;
        String firstName = null;
        String lastName = null;
        String experience = null;
        String mail = null;
        String enterprise = null;

        String getCandidate = "SELECT p.idPerson, p.uuidPerson, p.firstName, p.lastName, p.experience, p.mail, e.name " +
                "FROM Person p " +
                "INNER JOIN Enterprise e ON p.idEnterprise = e.idEnterprise " +
                "INNER JOIN Profile pr ON p.idPerson = pr.idProfile "+
                "WHERE p.idPerson = "+idCandidate+" "+
                "AND pr.isCandidate = "+1;

        try {
            ResultSet resultset = statement.executeQuery(getCandidate);
            if (resultset.next()) {
                idPerson = resultset.getString("idPerson");
                uuidString = resultset.getString("uuidPerson");
                uuid = UUID.fromString(uuidString);
                firstName = resultset.getString("firstName");
                lastName = resultset.getString("lastName");
                experience = resultset.getString("experience");
                mail = resultset.getString("mail");
                enterprise = resultset.getString("name");
            }else{
                throw new RecruiterNotFoundException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CandidateFullDto candidateFullDto = new CandidateFullDto(idPerson, uuid, firstName, lastName, experience, mail, enterprise, null, null);

        String getSkillsCandidate = "SELECT s.nameSkill, spc.isKeySkill " +
                "FROM Person p " +
                "INNER JOIN SkillPersonConf spc ON spc.idPerson = p.idPerson " +
                "INNER JOIN Skill s ON s.idSkill = spc.idSkill " +
                "WHERE p.idPerson = " + candidateFullDto.getId();
        try {
            ResultSet resultsetSkills = statement.executeQuery(getSkillsCandidate);
            List<String> keySkills = new ArrayList<>();
            List<String> skills = new ArrayList<>();
            while(resultsetSkills.next()) {
                String skill = resultsetSkills.getString("nameSkill");
                if(resultsetSkills.getInt("isKeySkill") == 0){
                    skills.add(skill);
                }else{
                    keySkills.add(skill);
                }
            }
            candidateFullDto.setKeySkills(keySkills);
            candidateFullDto.setSkills(skills);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("c 2");

        return candidateFullDto;
    }

    @Override
    public CandidateDto getCandidateByUUIDForSchedule(UUID uuidCandidate) {
        return null;
    }
}
