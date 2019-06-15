package infra.mysql;

import common.*;
import common.dto.RecruiterDto;
import common.dto.RecruiterFullDto;
import common.dto.SkillsDto;
import infra.DateMapper;
import infra.InfraDateForm;
import use_case.RecruitersRepository;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RecruitersRepositoryImpl implements RecruitersRepository {
    public Statement statement = null;
    public Connection connection;

    void mysqlConnection() {
        connection = DbConnect.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RecruiterFullDto> getRecruiters() {
        mysqlConnection();
        List<RecruiterFullDto> recruiters = new ArrayList<>();
        RecruiterFullDto recruiterDto;
        String getRecruiters = "SELECT p.uuidPerson, p.firstName, p.lastName, p.experience, p.mail, e.name FROM Person p " +
                "INNER JOIN Enterprise e ON p.id_enterprise = e.id_enterprise "+
                "INNER JOIN Profile pr ON p.idPerson = pr.idProfile " +
                "WHERE pr.isRecruiter = " +1;
        try {
            ResultSet resultset = statement.executeQuery(getRecruiters);
            while (resultset.next()) {
                String uuidString = resultset.getString("uuidPerson");
                UUID uuid = UUID.fromString(uuidString);
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                String experience = resultset.getString("experience");
                String mail = resultset.getString("mail");
                String enterprise = resultset.getString("name");
                recruiterDto = new RecruiterFullDto(uuid, firstName, lastName, experience, mail, enterprise, null, null);
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
                    "WHERE p.uuidPerson = " + "'"+recruiterFullDto.getUuid().toString()+"'";
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
        DbConnect.closeConnection(connection);
        return recruiters;
    }

    @Override
    public List<RecruiterDto> getRecruitersForSchedule() {
        mysqlConnection();
        List<RecruiterDto> recruiters = new ArrayList<>();
        RecruiterDto recruiterDto;
        List<LocalDateTime> localDateTimes;
        SkillsDto skillsDto;
        String getRecruiters = "SELECT p.uuidPerson, p.experience " +
                "FROM Person p "+
                "INNER JOIN Profile pr ON p.idPerson = pr.idProfile " +
                "AND pr.isRecruiter = " +1;
        try {
            ResultSet resultset = statement.executeQuery(getRecruiters);
            while (resultset.next()) {
                String uuidString = resultset.getString("uuidPerson");
                UUID uuid = UUID.fromString(uuidString);
                int experience = Integer.parseInt(resultset.getString("experience"));
                skillsDto = new SkillsDto();
                localDateTimes = new ArrayList<>();
                recruiterDto = new RecruiterDto(uuid, localDateTimes, skillsDto, experience);
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
                    "WHERE p.uuidPerson = " + "'"+recruiterDtoForSchedule.getUuid().toString()+"'";
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
            String getAvailabilitiesRecruiters = "SELECT ac.uuidPerson, ac.idAvailabilityMonth, ac.idAvailabilityDay, ac.idAvailabilityHour " +
                    "FROM Person p " +
                    "INNER JOIN PersonAvailabilityConf ac ON ac.uuidPerson = p.uuidPerson " +
                    "WHERE p.uuidPerson = " + "'"+recruiterDtoForSchedule.getUuid().toString()+"'";
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
        DbConnect.closeConnection(connection);
        return recruiters;
    }

    @Override
    public RecruiterFullDto getRecruiter(UUID uuid) {
        mysqlConnection();
        String uuidString;
        UUID uuidRecruiter = null;
        String firstName = null;
        String lastName = null;
        String experience = null;
        String mail = null;
        String enterprise = null;

            String getRecruiter = "SELECT p.uuidPerson, p.firstName, p.lastName, p.experience, p.mail, e.name " +
                    "FROM Person p INNER JOIN Enterprise e ON p.id_enterprise = e.id_enterprise " +
                    "INNER JOIN Profile pr ON p.idPerson = pr.idProfile " +
                    "AND pr.isRecruiter = " +1+" "+
                    "WHERE p.uuidPerson = " + "'"+uuid+"'";
            try {
                ResultSet resultset = statement.executeQuery(getRecruiter);
                if (resultset.next()) {
                    uuidString = resultset.getString("uuidPerson");
                    uuidRecruiter = UUID.fromString(uuidString);
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
        RecruiterFullDto recruiter = new RecruiterFullDto(uuidRecruiter, firstName, lastName, experience, mail, enterprise, null, null);

        String getSkillsRecruiter = "SELECT s.nameSkill, spc.isKeySkill " +
                "FROM Person p " +
                "INNER JOIN SkillPersonConf spc ON spc.idPerson = p.idPerson " +
                "INNER JOIN Skill s ON s.idSkill = spc.idSkill " +
                "WHERE p.uuidPerson = " + "'"+recruiter.getUuid().toString()+"'";
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
        DbConnect.closeConnection(connection);
        return recruiter;
    }

}
