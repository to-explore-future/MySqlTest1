package hierarchyDemo.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * c3po
 * 把c3po-config.properties 放到src下面 ，comboPooledDataSource 会自己去找，
 */
public class C3P0Util {

    public static ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

    public static DataSource getDataSource(){
        return comboPooledDataSource;
    }

    public static Connection getConnection() throws SQLException {
        return comboPooledDataSource.getConnection();
    }

    public static void closeAll(Connection connection, ResultSet resultSet, Statement statement){
        SqlUtil.closeAll(connection,resultSet,statement);
    }
}
