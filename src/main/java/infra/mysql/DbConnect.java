package infra.mysql;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnect {

    static Connection connection = null;

    public static Connection getConnection() {
        try (
                InputStream inputStream = new FileInputStream("/Users/Soat-AP/IdeaProjects/recruitment/jdbc.properties")) {
            Properties prop = new Properties();
            prop.load(inputStream);
            String url = prop.getProperty("db.url");
            String username = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
