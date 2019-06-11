package infra.mysql;

import common.*;
import common.dto.CandidateDto;
import common.dto.CandidateFullDto;
import common.dto.SkillsDto;
import use_case.CandidateRepository;

import java.sql.*;
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

        String getSkillsCandidate = "SELECT s.nameSkill, spc.isKeySkill " +
                "FROM Person p " +
                "INNER JOIN SkillPersonConf spc ON spc.idPerson = p.idPerson " +
                "INNER JOIN Skill s ON s.idSkill = spc.idSkill " +
                "WHERE p.uuidPerson = " + "'" + uuidCandidate.toString() + "' ";
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
            String getSkillsRecruiters = "SELECT s.nameSkill, spc.isKeySkill " +
                    "FROM Person p " +
                    "INNER JOIN SkillPersonConf spc ON spc.idPerson = p.idPerson " +
                    "INNER JOIN Skill s ON s.idSkill = spc.idSkill " +
                    "WHERE p.uuidPerson = " + "'" + candidateFullDto1.getUuid() + "'";
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
        String deleteCandidate = "Delete p "+
                "FROM Person p " +
                "INNER JOIN Profile pr ON p.idPerson = pr.idProfile " +
                "WHERE p.uuidPerson = " + "'" + uuid.toString() + "' " +
                "AND pr.isCandidate = " + 1;
        System.out.println(deleteCandidate);
        try {
            statement.execute(deleteCandidate);
            work = true;
        } catch (SQLException e) {
            e.printStackTrace();
            work = false;
        }

        DbConnect.closeConnection(connection);
        return work;
    }

    public boolean insertCandidate(CandidateFullDto candidateFullDto) {
        mysqlConnection();
        boolean work = false;
        String firstNameCandidate = candidateFullDto.getFirstName();
        String lastName = candidateFullDto.getLastName();
        String mail = candidateFullDto.getMail();
        int experience = candidateFullDto.getExperience();
        String id_entreprise = candidateFullDto.getEnterprise();
        int newIdCandidate = 0;
        String insertCandidate = "INSERT INTO Person " +
                "(firstName, lastName, mail, experience, id_enterprise)" +
                "VALUES " + firstNameCandidate + ", +" + lastName + ", +" + mail + ", +" + experience + ", +" + id_entreprise;
        try {
            statement.execute(insertCandidate);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet generatedKeys = null;
        try {
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                newIdCandidate = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String insertProfil = "INSERT INTO Profil " +
                "(idPerson, idCandidate, idRecruiter )" +
                "VALUES " + newIdCandidate + ", +" + 1 + ", +" + 0;
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
        mysqlConnection();
        System.out.println("connection");
        boolean work;
        String firstNameCandidate = candidate.getFirstName();
        String lastName = candidate.getLastName();
        String mail = candidate.getMail();
        int experience = candidate.getExperience();
        System.out.println("before request");
        String updateCandidate = "UPDATE Person " +
                "(firstName, lastName, mail, experience )" +
                "SET " + firstNameCandidate + ", +" + lastName + ", +" + mail + ", +" + experience + " " +
                "WHERE uuidPersion = " + candidate.getUuid().toString();
        System.out.println(updateCandidate);
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
}