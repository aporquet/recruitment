package infra.mysql;

import common.AnyEnterpriseFoundException;
import common.dto.SkillFullDto;
import use_case.SkillRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillRepositoryImpl implements SkillRepository {

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
    public List<SkillFullDto> getSkills() {
        mysqlConnection();
        List<SkillFullDto> skillFullDtos = new ArrayList<>();
        SkillFullDto skillFullDto;
        int idSkill;
        String nameSkill;
        String getSkills = "SELECT s.idSkill, s.nameSkill " +
                "FROM Skill s ";
        try {
            ResultSet resultset = statement.executeQuery(getSkills);
            while (resultset.next()) {
                idSkill = resultset.getInt("idSkill");
                nameSkill = resultset.getString("nameSkill");
                if (resultset == null) {
                    throw new AnyEnterpriseFoundException();
                }
                skillFullDto = new SkillFullDto(idSkill, nameSkill);
                skillFullDtos.add(skillFullDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbConnect.closeConnection(connection);
        return skillFullDtos;
    }
}
