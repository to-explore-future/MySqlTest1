package hierarchyDemo.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class SqlUtil {

    private static String driver;
    public static String dateBaseName;
    private static String user;
    private static String password;
    private static BasicDataSource basicDataSource = new BasicDataSource();


    private static String getUrl(String datebaseName) {
        return "jdbc:mysql://localhost:3306/" + datebaseName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    }

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("jdbc_config"));
            driver = properties.getProperty("driver");
            dateBaseName = properties.getProperty("dateBaseName");
            user = properties.getProperty("user");
            password = properties.getProperty("password");

            basicDataSource.setDriverClassName(driver);
            basicDataSource.setUsername(user);
            basicDataSource.setPassword(password);
            basicDataSource.setUrl(getUrl(dateBaseName));
            Class.forName(driver);
        } catch (Exception e) {
            throw new RuntimeException("驱动加载异常");
        }
    }

    public static Connection getConnection() throws Exception {
//        return DriverManager.getConnection(getUrl(dateBaseName), user, password);
        return basicDataSource.getConnection();
    }

    public static void closeAll(Connection connection, ResultSet resultSet, Statement statement) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
