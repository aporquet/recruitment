package infra.mysql;

import common.AnyEnterpriseFoundException;
import common.AnyInterviewFoundException;
import common.dto.EnterpriseDto;
import common.dto.InterviewFullDto;
import infra.InfraDateForm;
import use_case.EnterpriseRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EnterpriseRepositoryImpl implements EnterpriseRepository {

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
    public List<EnterpriseDto> getEnterprises() {
        mysqlConnection();
        List<EnterpriseDto> enterpriseDtoList = new ArrayList<>();
        EnterpriseDto enterpriseDto;
        int idEnterprise;
        String nameEnterprise;

        String getEnterprises = "SELECT e.id_enterprise, e.name " +
                "FROM Enterprise e ";

        try {
            ResultSet resultset = statement.executeQuery(getEnterprises);
            while (resultset.next()) {
                idEnterprise = resultset.getInt("id_enterprise");
                nameEnterprise = resultset.getString("name");
                if (resultset == null) {
                    throw new AnyEnterpriseFoundException();
                }
                enterpriseDto = new EnterpriseDto(idEnterprise, nameEnterprise);
                enterpriseDtoList.add(enterpriseDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbConnect.closeConnection(connection);
        return enterpriseDtoList;
    }
}
