package infra.mysql;

import common.dto.*;
import common.exceptions.AnyCandidateFoundException;
import common.exceptions.AnyInterviewFoundException;
import common.exceptions.CandidateNotFoundException;
import common.exceptions.CannotUpdateNullCandidateException;
import infra.DateMapper;
import infra.InfraDateForm;
import infra.service.MailConfig;
import org.springframework.mail.SimpleMailMessage;
import use_case.CandidateRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CandidateRepositoryImpl implements CandidateRepository {
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
    public CandidateFullDto getCandidate(UUID uuidCandidate) {
        mysqlConnection();
        String uuidString;
        UUID uuid = null;
        String firstName = null;
        String lastName = null;
        int experience = 0;
        String mail = null;
        String enterprise = null;

        String getCandidate = "SELECT p.uuidPerson, p.firstName, p.lastName, p.mail, p.experience, p.mail, e.name " +
                "FROM Person p " +
                "INNER JOIN Enterprise e ON p.id_enterprise = e.id_enterprise " +
                "INNER JOIN Profile pr ON p.idPerson = pr.idProfile " +
                "WHERE p.uuidPerson = " + "'" + uuidCandidate.toString() + "' " +
                "AND pr.isCandidate = " + 1;

        try {
            ResultSet resultset = statement.executeQuery(getCandidate);
            if (resultset.next()) {
                uuidString = resultset.getString("uuidPerson");
                uuid = UUID.fromString(uuidString);
                firstName = resultset.getString("firstName");
                lastName = resultset.getString("lastName");
                mail = resultset.getString("mail");
                experience = Integer.parseInt(resultset.getString("experience"));
                enterprise = resultset.getString("name");
            } else {
                throw new CandidateNotFoundException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CandidateFullDto candidateFullDto = new CandidateFullDto(uuid, firstName, lastName, mail, experience, enterprise, null, null);

        String getSkillsCandidate = "SELECT s.idSkill, s.nameSkill, spc.isKeySkill " +
                "FROM Person p " +
                "INNER JOIN SkillPersonConf spc ON spc.idPerson = p.idPerson " +
                "INNER JOIN Skill s ON s.idSkill = spc.idSkill " +
                "WHERE p.uuidPerson = " + "'" + uuidCandidate.toString() + "' ";
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

        DbConnect.closeConnection(connection);
        return candidateFullDto;
    }

    @Override
    public List<CandidateFullDto> getCandidates() {
        mysqlConnection();
        List<CandidateFullDto> candidateFullDtos = new ArrayList<>();
        CandidateFullDto candidateFullDto;
        String getCandidates = "SELECT p.uuidPerson, p.firstName, p.lastName, p.experience, p.mail, e.name FROM Person p " +
                "INNER JOIN Enterprise e ON p.id_enterprise = e.id_enterprise " +
                "INNER JOIN Profile pr ON p.idPerson = pr.idProfile " +
                "WHERE pr.isCandidate = " + 1;
        try {
            ResultSet resultset = statement.executeQuery(getCandidates);
            while (resultset.next()) {
                UUID uuid = UUID.fromString(resultset.getString("uuidPerson"));
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                int experience = Integer.parseInt(resultset.getString("experience"));
                String mail = resultset.getString("mail");
                String enterprise = resultset.getString("name");
                candidateFullDto = new CandidateFullDto(uuid, firstName, lastName, mail, experience, enterprise, null, null);
                candidateFullDtos.add(candidateFullDto);
                if (resultset == null) {
                    throw new AnyCandidateFoundException();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (CandidateFullDto candidateFullDto1 : candidateFullDtos) {
            System.out.println(candidateFullDto1.getUuid().toString().getClass().getName());
            System.out.println(candidateFullDto1.getUuid());
            String getSkillsRecruiters = "SELECT s.idSkill, s.nameSkill, spc.isKeySkill " +
                    "FROM Person p " +
                    "INNER JOIN SkillPersonConf spc ON spc.idPerson = p.idPerson " +
                    "INNER JOIN Skill s ON s.idSkill = spc.idSkill " +
                    "WHERE p.uuidPerson = " + "'" + candidateFullDto1.getUuid() + "'";
            try {
                ResultSet resultsetSkills = statement.executeQuery(getSkillsRecruiters);
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
                candidateFullDto1.setKeySkills(keySkills);
                candidateFullDto1.setSkills(skills);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DbConnect.closeConnection(connection);
        return candidateFullDtos;
    }

    @Override
    public CandidateDto getCandidateForSchedule(UUID uuidCandidate) {
        mysqlConnection();
        String uuidString;
        UUID uuid = null;
        int experience = 0;
        SkillsDto skillsDto;
        String getCandidate = "SELECT p.uuidPerson, p.experience " +
                "FROM Person p " +
                "WHERE p.uuidPerson = " + "'" + uuidCandidate.toString() + "'";
        try {
            ResultSet resultset = statement.executeQuery(getCandidate);
            if (resultset.next()) {
                uuidString = resultset.getString("uuidPerson");
                uuid = UUID.fromString(uuidString);
                experience = Integer.parseInt(resultset.getString("experience"));
            } else {
                throw new CandidateNotFoundException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CandidateDto candidateDto = new CandidateDto(uuid, null, experience);

        String getSkillsCandidate = "SELECT s.nameSkill, spc.isKeySkill " +
                "FROM Person p " +
                "INNER JOIN SkillPersonConf spc ON spc.idPerson = p.idPerson " +
                "INNER JOIN Skill s ON s.idSkill = spc.idSkill " +
                "INNER JOIN Profile pr ON p.idPerson = pr.idProfile " +
                "WHERE p.uuidPerson = " + "'" + uuidCandidate.toString() + "'" +
                "AND pr.isCandidate = " + 1;
        try {
            ResultSet resultsetSkills = statement.executeQuery(getSkillsCandidate);
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
            candidateDto.setSkills(skillsDto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbConnect.closeConnection(connection);
        return candidateDto;
    }

    public boolean deleteCandidate(UUID uuid) {
        mysqlConnection();
        boolean work;

        int idInterview = 0;
        String uuidStringCandidate = "";
        UUID uuidCandidate = null;
        String uuidStringRecruiter = "";
        UUID uuidRecruiter = null;
        int idAvailabilityMonth = 0;
        int idAvailabilityDay = 0;
        int idAvailabilityHour = 0;

        String deleteCandidate = "Delete p " +
                "FROM Person p " +
                "INNER JOIN Profile pr ON p.idPerson = pr.idProfile " +
                "WHERE p.uuidPerson = " + "'" + uuid.toString() + "' " +
                "AND pr.isCandidate = " + 1;
        try {
            statement.execute(deleteCandidate);
            work = true;
        } catch (SQLException e) {
            e.printStackTrace();
            work = false;
        }

        // Get interview informations before reinsert recruiter availability
        String selectInterview = "SELECT i.idInterview, i.uuidRecruiter, i.uuidCandidate, " +
                "i.idAvailabilityMonth, i.idAvailabilityDay, i.idAvailabilityHour " +
                "FROM Interview i " +
                "WHERE i.uuidCandidate = " + "'" + uuid.toString() + "'";

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

        if(idInterview == 0){ return work = true; }

        DateMapper dateMapper = new DateMapper();
        InfraDateForm infraDateFormToDelete = new InfraDateForm(idAvailabilityMonth, idAvailabilityDay, idAvailabilityHour);
        LocalDateTime dateTime = dateMapper.mapInfraDateFormToDateTime(infraDateFormToDelete);
        InterviewDeleterDto interviewDeleterDto = new InterviewDeleterDto(idInterview, dateTime, uuidRecruiter, uuidCandidate);

        InfraDateForm infraDateForm = dateMapper.mapDateTimeToInfraDateForm(interviewDeleterDto.getDateInterview());
        int hourAvailability = infraDateForm.getHour();
        int dayAvailability = infraDateForm.getDay();
        int monthAvailability = infraDateForm.getMonth();

        // Delete interview
        String deleteCandidateInterview = "Delete i " +
                "FROM Interview i " +
                "WHERE i.uuidCandidate = " + "'" + uuid.toString() + "'";

        try {
            statement.execute(deleteCandidateInterview);
            work = true;
        } catch (SQLException e) {
            e.printStackTrace();
            work = false;
        }

        // Reinsert Recruiter availabilities
        String reInsertRecruiterAvailability = "INSERT INTO PersonAvailabilityConf" +
                "(uuidPerson, idAvailabilityMonth, idAvailabilityDay, idAvailabilityHour)" +
                "VALUES (" + "'" + interviewDeleterDto.getUuidRecruiter().toString() + "', " + monthAvailability + ", " + dayAvailability + ", " + hourAvailability + ")";

        try {
            statement.executeUpdate(reInsertRecruiterAvailability);
            work = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Get mail to notify recruiter's interview has benn canceled
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

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("recruitorapp@gmail.com");
        mailMessage.setTo(mailRecruiter);
        mailMessage.setSubject("Interview schedule");
        mailMessage.setText("Your interview scheduled for " + interviewDeleterDto.getDateInterview() + " has been " +
                "canceled because candidate is out of process");
        MailConfig.getMailConfig().send(mailMessage);

        DbConnect.closeConnection(connection);
        return work;
    }

    public boolean insertCandidate(CandidateFullDto candidateFullDto) {
        mysqlConnection();
        boolean work = false;
        String uuidCandidate = candidateFullDto.getUuid().toString();
        String firstNameCandidate = candidateFullDto.getFirstName();
        String lastName = candidateFullDto.getLastName();
        String mail = candidateFullDto.getMail();
        int experience = candidateFullDto.getExperience();
        String id_enterprise = candidateFullDto.getEnterprise();
        int newIdCandidate = 0;
        ResultSet generatedKeys = null;
        String insertCandidate = "INSERT INTO Person " +
                "(uuidPerson,firstName, lastName, mail, experience, id_enterprise) " +
                "VALUES (" + "'" + uuidCandidate + "', " +
                "'" + firstNameCandidate + "', " +
                "'" + lastName + "', " +
                "'" + mail + "', " +
                experience + ", " +
                "'" + id_enterprise + "')";
        try {
            statement.execute(insertCandidate, statement.RETURN_GENERATED_KEYS);
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                newIdCandidate = generatedKeys.getInt(1);
                work = true;
            }
        } catch (SQLException e) {
            work = false;
            e.printStackTrace();
        }

        String insertProfil = "INSERT INTO Profile " +
                "(idProfile, isCandidate, isRecruiter) " +
                "VALUES (" + newIdCandidate + ", +" + 1 + ", +" + 0 +")";
        try {
            statement.execute(insertProfil);
            work = true;
        } catch (SQLException e) {
            work = false;
            e.printStackTrace();
        }
        DbConnect.closeConnection(connection);
        return work;
    }

    public boolean updateCandidate(CandidateFullDto candidate) {
        if (candidate.getUuid() == null) {
            throw new CannotUpdateNullCandidateException();
        }
        mysqlConnection();
        boolean work;
        String firstNameCandidate = candidate.getFirstName();
        String lastName = candidate.getLastName();
        String mail = candidate.getMail();
        int experience = candidate.getExperience();
        String updateCandidate = "UPDATE recruitment.Person " +
                "SET firstName = " + "'" + firstNameCandidate + "', " +
                "lastName = " + "'" + lastName + "', " +
                "mail = " + "'" + mail + "', " +
                "experience = " + "'" + experience + "' " +
                "WHERE uuidPerson = " + "'" + candidate.getUuid().toString() + "'";
        try {
            statement.execute(updateCandidate);
            work = true;
        } catch (SQLException e) {
            e.printStackTrace();
            work = false;
        }
        DbConnect.closeConnection(connection);
        return work;
    }

    public CandidateFullDto generateUUID(CandidateFullDto candidate) {
        boolean uuidExist = true;
        UUID uuidCandidate = UUID.randomUUID();
        while (uuidExist) {
            uuidCandidate = UUID.randomUUID();
            List<CandidateFullDto> candidateFullDtos = this.getCandidates();
            uuidExist = candidateFullDtos.stream()
                    .map(CandidateFullDto::getUuid)
                    .anyMatch(uuidCandidate::equals);
        }
        candidate.setUuid(uuidCandidate);
        return candidate;
    }
}
