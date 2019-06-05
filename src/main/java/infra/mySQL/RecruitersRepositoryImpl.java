package infra.mySQL;

import common.*;
import infra.DateMapper;
import infra.InfraDateForm;
import use_case.RecruitersRepository;
import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class RecruitersRepositoryImpl implements RecruitersRepository {
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
    public List<RecruiterFullDto> getRecruiters() {
        mysqlConnection();
        List<RecruiterFullDto> recruiters = new ArrayList<>();
        RecruiterFullDto recruiterDto;
        String getRecruiters = "SELECT p.idPerson, p.uuidPerson, p.firstName, p.lastName, p.experience, p.mail, e.name FROM Person p " +
                "INNER JOIN Enterprise e ON p.idEnterprise = e.idEnterprise "+
                "INNER JOIN Profile pr ON p.idPerson = pr.idProfile " +
                "WHERE pr.isRecruiter = " +1;
        try {
            ResultSet resultset = statement.executeQuery(getRecruiters);
            while (resultset.next()) {
                String uuidString = resultset.getString("uuidPerson");
                UUID uuid = UUID.fromString(uuidString);
                String id = resultset.getString("idPerson");
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                String experience = resultset.getString("experience");
                String mail = resultset.getString("mail");
                String enterprise = resultset.getString("name");
                recruiterDto = new RecruiterFullDto(id, uuid, firstName, lastName, experience, mail, enterprise, null, null);
                recruiters.add(recruiterDto);
                if (resultset == null) {
                    throw new AnyRecruiterFoundException();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(RecruiterFullDto recruiterFullDto : recruiters){
            String getSkillsRecruiters = "SELECT s.nameSkill, spc.isKeySkill " +
                    "FROM Person p " +
                    "INNER JOIN SkillPersonConf spc ON spc.idPerson = p.idPerson " +
                    "INNER JOIN Skill s ON s.idSkill = spc.idSkill " +
                    "WHERE p.idPerson = " + recruiterFullDto.getId();
            try {
                ResultSet resultsetSkills = statement.executeQuery(getSkillsRecruiters);
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
                recruiterFullDto.setKeySkills(keySkills);
                recruiterFullDto.setSkills(skills);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return recruiters;
    }

    @Override
    public List<RecruiterDto> getRecruitersForSchedule() {
        mysqlConnection();
        List<RecruiterDto> recruiters = new ArrayList<>();
        RecruiterDto recruiterDto;
        List<LocalDateTime> localDateTimes;
        SkillsDto skillsDto;
        String getRecruiters = "SELECT p.idPerson, p.uuidPerson, p.experience " +
                "FROM Person p "+
                "INNER JOIN Profile pr ON p.idPerson = pr.idProfile " +
                "AND pr.isRecruiter = " +1;
        try {
            ResultSet resultset = statement.executeQuery(getRecruiters);
            while (resultset.next()) {
                String uuidString = resultset.getString("uuidPerson");
                int id = Integer.parseInt(resultset.getString("idPerson"));
                int experience = Integer.parseInt(resultset.getString("experience"));
                skillsDto = new SkillsDto();
                localDateTimes = new ArrayList<>();
                recruiterDto = new RecruiterDto(id, localDateTimes, skillsDto, experience);
                recruiters.add(recruiterDto);
                if (resultset == null) {
                    throw new AnyRecruiterFoundException();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(RecruiterDto recruiterDtoForSchedule : recruiters) {
            String getSkillsRecruiters = "SELECT s.nameSkill, spc.isKeySkill " +
                    "FROM Person p " +
                    "INNER JOIN SkillPersonConf spc ON spc.idPerson = p.idPerson " +
                    "INNER JOIN Skill s ON s.idSkill = spc.idSkill " +
                    "WHERE p.idPerson = " + recruiterDtoForSchedule.getId();
            try {
                ResultSet resultsetSkills = statement.executeQuery(getSkillsRecruiters);
                List<String> keySkills = new ArrayList<>();
                List<String> skills = new ArrayList<>();
                skillsDto = new SkillsDto();
                while (resultsetSkills.next()) {
                    String skill = resultsetSkills.getString("nameSkill");
                    if (resultsetSkills.getInt("isKeySkill") == 0) {
                        skills.add(skill);
                    } else {
                        keySkills.add(skill);
                    }
                }
                skillsDto.setKeySkills(keySkills);
                skillsDto.setOtherSkills(skills);
                recruiterDtoForSchedule.setRecruiterSkills(skillsDto);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for(RecruiterDto recruiterDtoForSchedule : recruiters) {
            String getAvailabilitiesRecruiters = "SELECT p.idPerson, ac.idPerson, ac.idAvailabilityMonth, ac.idAvailabilityDay, ac.idAvailabilityHour " +
                    "FROM Person p " +
                    "INNER JOIN PersonAvailabilityConf ac ON ac.idPerson = p.idPerson " +
                    "WHERE p.idPerson = " + recruiterDtoForSchedule.getId();
            try {
                ResultSet resultsetAvailabilities = statement.executeQuery(getAvailabilitiesRecruiters);
                List<LocalDateTime> availabilities = new ArrayList<>();
                DateMapper dateMapper = new DateMapper();
                while (resultsetAvailabilities.next()) {
                    int month = Integer.parseInt(resultsetAvailabilities.getString("idAvailabilityMonth"));
                    int day = Integer.parseInt(resultsetAvailabilities.getString("idAvailabilityDay"));
                    int hour = Integer.parseInt(resultsetAvailabilities.getString("idAvailabilityHour"));
                    InfraDateForm infraDateForm = new InfraDateForm(month, day, hour);
                    LocalDateTime dateTime = dateMapper.mapInfraDateFormToDateTime(infraDateForm);
                    availabilities.add(dateTime);
                }
                recruiterDtoForSchedule.setAvailabilities(availabilities);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return recruiters;
    }


    @Override
    public RecruiterFullDto getRecruiter(String id) {
        mysqlConnection();
        String idPerson = null;
        String uuidString;
        UUID uuid = null;
        String firstName = null;
        String lastName = null;
        String experience = null;
        String mail = null;
        String enterprise = null;

            String getRecruiter = "SELECT p.idPerson, p.uuidPerson, p.firstName, p.lastName, p.experience, p.mail, e.name " +
                    "FROM Person p INNER JOIN Enterprise e ON p.idEnterprise = e.idEnterprise " +
                    "INNER JOIN Profile pr ON p.idPerson = pr.idProfile " +
                    "AND pr.isRecruiter = " +1+" "+
                    "WHERE p.idPerson = "+id;
            try {
                ResultSet resultset = statement.executeQuery(getRecruiter);
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
        RecruiterFullDto recruiter = new RecruiterFullDto(idPerson, uuid, firstName, lastName, experience, mail, enterprise, null, null);

        String getSkillsRecruiter = "SELECT s.nameSkill, spc.isKeySkill " +
                "FROM Person p " +
                "INNER JOIN SkillPersonConf spc ON spc.idPerson = p.idPerson " +
                "INNER JOIN Skill s ON s.idSkill = spc.idSkill " +
                "WHERE p.idPerson = " + recruiter.getId();
        try {
            ResultSet resultsetSkills = statement.executeQuery(getSkillsRecruiter);
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
            recruiter.setKeySkills(keySkills);
            recruiter.setSkills(skills);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recruiter;
    }

}
