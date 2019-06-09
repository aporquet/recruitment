package infra.mysql;

import common.*;
import common.dto.CandidateFullDto;
import common.dto.InterviewDto;
import common.dto.InterviewFullDto;
import common.dto.RecruiterFullDto;
import infra.DateMapper;
import infra.InfraDateForm;
import use_case.InterviewRespository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InterviewRepositoryImpl implements InterviewRespository {

    public Statement statement = null;
    Connection connection;

    void mysqlConnection() {
        connection = DbConnect.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(InterviewDto interviewDto) {
        mysqlConnection();
        LocalDateTime dateTime = interviewDto.getDateTime();
        DateMapper dateMapper = new DateMapper();
        InfraDateForm infraDateForm = dateMapper.mapDateTimeToInfraDateForm(dateTime);
        int idRecruiter = 0;
        int idCandidate = 0;
        int hourAvailability = infraDateForm.getHour();
        int dayAvailability = infraDateForm.getDay();
        int monthAvailability = infraDateForm.getMonth();

        String getRecruiter = "SELECT idPerson FROM Person " +
                "WHERE uuidPerson = "+interviewDto.getUuidRecruiter().toString();
        ResultSet rsRecruiter = null;
        try {
            rsRecruiter = statement.executeQuery(getRecruiter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!rsRecruiter.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                idRecruiter = rsRecruiter.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String getCandidate = "SELECT idPerson FROM Person " +
                "WHERE uuidPerson = "+interviewDto.getUuidCandidate().toString();
        ResultSet rsCandidate = null;
        try {
            rsCandidate = statement.executeQuery(getCandidate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!rsCandidate.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                idCandidate = rsCandidate.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String postInterview = "INSERT INTO Interview " +
                "(idCandidate, idRecruiter, idAvailabilityHour, idAvailabilityDay, idAvailabilityMonth )" +
                "VALUES " + idCandidate + ", +" + idRecruiter +
                ", +" + hourAvailability + ", +" + dayAvailability + ", +" + monthAvailability;
        try {
            statement.execute(postInterview);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String deleteRecruiterAvailability = "Delete " +
                "FROM PersonAvailabilityConf " +
                "WHERE idPerson = "+ idRecruiter;
        try {
            statement.execute(deleteRecruiterAvailability);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int idInterview) {

    }

    @Override
    public List<InterviewFullDto> getInterviews() {
        mysqlConnection();
        List<InterviewFullDto> interviewFullDtos = new ArrayList<>();
        InterviewFullDto interviewFullDto;

        int idInterview;
        LocalDateTime dateInterview;
        CandidateFullDto candidateFullDto;
        RecruiterFullDto recruiterFullDto;

        DateMapper dateMapper = new DateMapper();

        String getInterviews = "SELECT i.idInterview, i.idCandidate, i.idRecruiter," +
                " i.idAvailabilityMonth, i.idAvailabilityDay, i.idAvailabilityHour " +
                "FROM Interview i ";
        try {
            ResultSet resultset = statement.executeQuery(getInterviews);
            while (resultset.next()) {
                idInterview = resultset.getInt("idInterview");
                int idAvailabilityMonth = resultset.getInt("idAvailabilityMonth");
                int idAvailabilityDay = resultset.getInt("idAvailabilityDay");
                int idAvailabilityHour = resultset.getInt("idAvailabilityHour");
                InfraDateForm infraDateForm = new InfraDateForm(idAvailabilityMonth, idAvailabilityDay, idAvailabilityHour);
                dateInterview = dateMapper.mapInfraDateFormToDateTime(infraDateForm);
                interviewFullDto = new InterviewFullDto(idInterview, null, null, dateInterview);
                interviewFullDtos.add(interviewFullDto);
                if (resultset == null) {
                    throw new AnyInterviewFoundException();
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        for(InterviewFullDto interviewFullDto1 : interviewFullDtos) {
            String uuidStringRecruiter;
            UUID uuidRecruiter = null;
            String firstNameRecruiter = null;
            String lastNameRecruiter = null;
            String experienceRecruiter = null;
            String mailRecruiter = null;
            String enterpriseRecruiter = null;

            String getRecruiter = "SELECT p.uuidPerson, p.firstName, p.lastName, p.experience, p.mail, e.name FROM Person p " +
                    "INNER JOIN Enterprise e ON p.id_enterprise = e.id_enterprise " +
                    "INNER JOIN Profile pr ON p.idPerson = pr.idProfile " +
                    "INNER JOIN Interview i ON i.idRecruiter = p.idPerson " +
                    "WHERE pr.isRecruiter = " + 1 + " " +
                    "AND i.idInterview = " + interviewFullDto1.getIdInterview();
            try {
                ResultSet resultsetRecruiter = statement.executeQuery(getRecruiter);
                while (resultsetRecruiter.next()) {
                    uuidStringRecruiter = resultsetRecruiter.getString("uuidPerson");
                    uuidRecruiter = UUID.fromString(uuidStringRecruiter);
                    firstNameRecruiter = resultsetRecruiter.getString("firstName");
                    lastNameRecruiter = resultsetRecruiter.getString("lastName");
                    experienceRecruiter = resultsetRecruiter.getString("experience");
                    mailRecruiter = resultsetRecruiter.getString("mail");
                    enterpriseRecruiter = resultsetRecruiter.getString("name");
                    if (resultsetRecruiter == null) {
                        throw new AnyRecruiterFoundException();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            recruiterFullDto = new RecruiterFullDto(uuidRecruiter, firstNameRecruiter, lastNameRecruiter, mailRecruiter, experienceRecruiter, enterpriseRecruiter, null, null);

            String getSkillsRecruiters = "SELECT s.nameSkill, spc.isKeySkill " +
                    "FROM Person p " +
                    "INNER JOIN SkillPersonConf spc ON spc.idPerson = p.idPerson " +
                    "INNER JOIN Skill s ON s.idSkill = spc.idSkill " +
                    "WHERE p.uuidPerson = " + "'" + recruiterFullDto.getUuid().toString() + "'";
            try {
                ResultSet resultsetSkills = statement.executeQuery(getSkillsRecruiters);
                List<String> keySkills = new ArrayList<>();
                List<String> skills = new ArrayList<>();
                while (resultsetSkills.next()) {
                    String skill = resultsetSkills.getString("nameSkill");
                    if (resultsetSkills.getInt("isKeySkill") == 0) {
                        skills.add(skill);
                    } else {
                        keySkills.add(skill);
                    }
                }
                recruiterFullDto.setKeySkills(keySkills);
                recruiterFullDto.setSkills(skills);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            mysqlConnection();
            String uuidStringCandidate;
            UUID uuidCandidate = null;
            String firstNameCandidate = null;
            String lastNameCandidate = null;
            int experienceCandidate = 0;
            String mailCandidate = null;
            String enterpriseCandidate = null;

            String getCandidate = "SELECT p.uuidPerson, p.firstName, p.lastName, p.experience, p.mail, e.name FROM Person p " +
                    "INNER JOIN Enterprise e ON p.id_enterprise = e.id_enterprise " +
                    "INNER JOIN Profile pr ON p.idPerson = pr.idProfile " +
                    "INNER JOIN Interview i ON i.idCandidate = p.idPerson " +
                    "WHERE pr.isCandidate = " + 1 + " " +
                    "AND i.idInterview = " + interviewFullDto1.getIdInterview();
            try {
                ResultSet resultsetCandidate = statement.executeQuery(getCandidate);
                while (resultsetCandidate.next()) {
                    uuidStringCandidate = resultsetCandidate.getString("uuidPerson");
                    uuidCandidate = UUID.fromString(uuidStringCandidate);
                    firstNameCandidate = resultsetCandidate.getString("firstName");
                    lastNameCandidate = resultsetCandidate.getString("lastName");
                    experienceCandidate = Integer.parseInt(resultsetCandidate.getString("experience"));
                    mailCandidate = resultsetCandidate.getString("mail");
                    enterpriseCandidate = resultsetCandidate.getString("name");
                    if (resultsetCandidate == null) {
                        throw new AnyCandidateFoundException();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            candidateFullDto = new CandidateFullDto(uuidCandidate, firstNameCandidate, lastNameCandidate, mailCandidate, experienceCandidate, enterpriseCandidate, null, null);

            String getSkillsCandidate = "SELECT s.nameSkill, spc.isKeySkill " +
                    "FROM Person p " +
                    "INNER JOIN SkillPersonConf spc ON spc.idPerson = p.idPerson " +
                    "INNER JOIN Skill s ON s.idSkill = spc.idSkill " +
                    "WHERE p.uuidPerson = " + "'" + candidateFullDto.getUuid().toString() + "' ";
            try {
                ResultSet resultsetSkills = statement.executeQuery(getSkillsCandidate);
                List<String> keySkills = new ArrayList<>();
                List<String> skills = new ArrayList<>();
                while (resultsetSkills.next()) {
                    String skill = resultsetSkills.getString("nameSkill");
                    if (resultsetSkills.getInt("isKeySkill") == 0) {
                        skills.add(skill);
                    } else {
                        keySkills.add(skill);
                    }
                }
                candidateFullDto.setKeySkills(keySkills);
                candidateFullDto.setSkills(skills);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            interviewFullDto1.setRecruiterFullDto(recruiterFullDto);
            interviewFullDto1.setCandidateFullDto(candidateFullDto);
        }
                DbConnect.closeConnection(connection);
                return interviewFullDtos;
    }
}
