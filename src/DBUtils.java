import hierarchyDemo.util.SqlUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtils {

    private static BasicDataSource dataSource;

    static {
        try {
            Properties properties = new Properties();
//            properties.load(new FileInputStream("dbcpconfig.properties"));
            //这种方式获取的流会自动在src目录下查找文件生成流
            properties.load(DBUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));
            dataSource = BasicDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeAll(Connection connection, ResultSet resultSet, Statement statement){
        SqlUtil.closeAll(connection,resultSet,statement);
    }




}
