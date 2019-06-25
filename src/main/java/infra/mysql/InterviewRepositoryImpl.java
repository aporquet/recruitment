package infra.mysql;

import common.dto.*;
import common.exceptions.AnyCandidateFoundException;
import common.exceptions.AnyInterviewFoundException;
import common.exceptions.AnyRecruiterFoundException;
import infra.DateMapper;
import infra.InfraDateForm;
import infra.service.MailConfig;
import org.springframework.mail.SimpleMailMessage;
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
        int hourAvailability = infraDateForm.getHour();
        int dayAvailability = infraDateForm.getDay();
        int monthAvailability = infraDateForm.getMonth();

        String postInterview = "INSERT INTO Interview " +
                "(uuidCandidate, uuidRecruiter, idAvailabilityHour, idAvailabilityDay, idAvailabilityMonth)" +
                " VALUES (" + "'" + interviewDto.getUuidCandidate().toString() + "', "
                + "'" + interviewDto.getUuidRecruiter().toString() + "', "
                + hourAvailability + ", " + dayAvailability + ", " + monthAvailability + ")";
        try {
            statement.execute(postInterview);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String deleteRecruiterAvailability = "Delete " +
                "FROM PersonAvailabilityConf " +
                "WHERE uuidPerson = " + "'" + interviewDto.getUuidRecruiter().toString() + "' AND " +
                "idAvailabilityMonth = " + monthAvailability + " AND " +
                "idAvailabilityDay = " + dayAvailability + " AND " +
                "idAvailabilityHour = " + hourAvailability;
        try {
            statement.execute(deleteRecruiterAvailability);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Get mail before notify interview actors
        String mailRecruiter = "SELECT p.mail " +
                "FROM Person p " +
                "WHERE p.uuidPerson = " + "'" + interviewDto.getUuidRecruiter().toString() + "'";

        ResultSet mailRecruiterResult = null;
        try {
            mailRecruiterResult = statement.executeQuery(mailRecruiter);
            while (mailRecruiterResult.next()) {
                mailRecruiter = mailRecruiterResult.getString("mail");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String mailCandidate = "SELECT p.mail " +
                "FROM Person p " +
                "WHERE p.uuidPerson = " + "'" + interviewDto.getUuidCandidate().toString() + "'";

        ResultSet mailCandidateResult = null;
        try {
            mailCandidateResult = statement.executeQuery(mailCandidate);
            while (mailCandidateResult.next()) {
                mailCandidate = mailCandidateResult.getString("mail");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("recruitorapp@gmail.com");
        mailMessage.setTo(mailRecruiter, mailCandidate);
        mailMessage.setSubject("Interview schedule");
        mailMessage.setText("Your interview is schedule for " + interviewDto.getDateTime().toString());
        MailConfig.getMailConfig().send(mailMessage);

        DbConnect.closeConnection(connection);
    }

    @Override
    public boolean deleteInterview(int idInterview) {
        mysqlConnection();
        String uuidStringCandidate = "";
        UUID uuidCandidate = null;
        String uuidStringRecruiter = "";
        UUID uuidRecruiter = null;
        int idAvailabilityMonth = 0;
        int idAvailabilityDay = 0;
        int idAvailabilityHour = 0;
        boolean work = false;

        String selectInterview = "SELECT i.idInterview, i.uuidRecruiter, i.uuidCandidate, " +
                "i.idAvailabilityMonth, i.idAvailabilityDay, i.idAvailabilityHour " +
                "FROM Interview i " +
                "WHERE idInterview = " + idInterview;

        ResultSet resultsetInterview = null;
        try {
            resultsetInterview = statement.executeQuery(selectInterview);
            while (resultsetInterview.next()) {
                idInterview = resultsetInterview.getInt("idInterview");
                uuidStringCandidate = resultsetInterview.getString("uuidCandidate");
                uuidStringRecruiter = resultsetInterview.getString("uuidRecruiter");
                uuidCandidate = UUID.fromString(uuidStringCandidate);
                uuidRecruiter = UUID.fromString(uuidStringRecruiter);
                idAvailabilityMonth = resultsetInterview.getInt("idAvailabilityMonth");
                idAvailabilityDay = resultsetInterview.getInt("idAvailabilityDay");
                idAvailabilityHour = resultsetInterview.getInt("idAvailabilityHour");
                work = true;
            }
            if (resultsetInterview == null) {
                throw new AnyInterviewFoundException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DateMapper dateMapper = new DateMapper();
        InfraDateForm infraDateFormToDelete = new InfraDateForm(idAvailabilityMonth, idAvailabilityDay, idAvailabilityHour);
        LocalDateTime dateTime = dateMapper.mapInfraDateFormToDateTime(infraDateFormToDelete);
        InterviewDeleterDto interviewDeleterDto = new InterviewDeleterDto(idInterview, dateTime, uuidRecruiter, uuidCandidate);

        InfraDateForm infraDateForm = dateMapper.mapDateTimeToInfraDateForm(interviewDeleterDto.getDateInterview());
        int hourAvailability = infraDateForm.getHour();
        int dayAvailability = infraDateForm.getDay();
        int monthAvailability = infraDateForm.getMonth();
        String deleteInterview = "DELETE " +
                "FROM Interview " +
                "WHERE idInterview = " + interviewDeleterDto.getIdInterview();
        try {
            statement.executeUpdate(deleteInterview);
            work = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String reInsertRecruiterAvailability = "INSERT INTO PersonAvailabilityConf" +
                "(uuidPerson, idAvailabilityMonth, idAvailabilityDay, idAvailabilityHour)" +
                "VALUES (" + "'" + interviewDeleterDto.getUuidRecruiter().toString() + "', " + monthAvailability + ", " + dayAvailability + ", " + hourAvailability + ")";

        try {
            statement.executeUpdate(reInsertRecruiterAvailability);
            work = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Get mail before notify interview actors
        String mailRecruiter = "SELECT p.mail " +
                "FROM Person p " +
                "WHERE p.uuidPerson = " + "'" + interviewDeleterDto.getUuidRecruiter().toString() + "'";

        ResultSet mailRecruiterResult = null;
        try {
            mailRecruiterResult = statement.executeQuery(mailRecruiter);
            while (mailRecruiterResult.next()) {
                mailRecruiter = mailRecruiterResult.getString("mail");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String mailCandidate = "SELECT p.mail " +
                "FROM Person p " +
                "WHERE p.uuidPerson = " + "'" + interviewDeleterDto.getUuidCandidate().toString() + "'";

        ResultSet mailCandidateResult = null;
        try {
            mailCandidateResult = statement.executeQuery(mailCandidate);
            while (mailCandidateResult.next()) {
                mailCandidate = mailCandidateResult.getString("mail");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("recruitorapp@gmail.com");
        mailMessage.setTo(mailRecruiter, mailCandidate);
        mailMessage.setSubject("Interview schedule");
        mailMessage.setText("Your interview scheduled for " + interviewDeleterDto.getDateInterview() + " has been canceled");
        MailConfig.getMailConfig().send(mailMessage);

        DbConnect.closeConnection(connection);
        return work;
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

        String getInterviews = "SELECT i.idInterview, i.uuidCandidate, i.uuidRecruiter," +
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (InterviewFullDto interviewFullDto1 : interviewFullDtos) {
            String uuidStringRecruiter;
            UUID uuidRecruiter = null;
            String firstNameRecruiter = null;
            String lastNameRecruiter = null;
            String experienceRecruiter = null;
            String mailRecruiter = null;
            String enterpriseRecruiter = null;

            String getRecruiter = "SELECT p.uuidPerson, p.firstName, p.lastName, p.experience, p.mail, e.name " +
                    "FROM Person p " +
                    "INNER JOIN Enterprise e ON p.id_enterprise = e.id_enterprise " +
                    "INNER JOIN Profile pr ON p.idPerson = pr.idProfile " +
                    "INNER JOIN Interview i ON i.uuidRecruiter = p.uuidPerson " +
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

            recruiterFullDto = new RecruiterFullDto(uuidRecruiter, firstNameRecruiter, lastNameRecruiter, experienceRecruiter, mailRecruiter, enterpriseRecruiter, null, null);

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
                    "INNER JOIN Interview i ON i.uuidCandidate = p.uuidPerson " +
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

            String getSkillsCandidate = "SELECT s.idSkill, s.nameSkill, spc.isKeySkill " +
                    "FROM Person p " +
                    "INNER JOIN SkillPersonConf spc ON spc.idPerson = p.idPerson " +
                    "INNER JOIN Skill s ON s.idSkill = spc.idSkill " +
                    "WHERE p.uuidPerson = " + "'" + candidateFullDto.getUuid().toString() + "' ";
            try {
                ResultSet resultsetSkills = statement.executeQuery(getSkillsCandidate);
                List<SkillFullDto> keySkills = new ArrayList<>();
                List<SkillFullDto> skills = new ArrayList<>();
                while (resultsetSkills.next()) {
                    int idSkill = resultsetSkills.getInt("idSkill");
                    String nameSkill = resultsetSkills.getString("nameSkill");
                    SkillFullDto skill = new SkillFullDto(idSkill, nameSkill);
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
