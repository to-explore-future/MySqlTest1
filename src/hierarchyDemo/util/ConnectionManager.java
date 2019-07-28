package hierarchyDemo.util;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionManager {

    private static ThreadLocal<Connection> threadLocal= new ThreadLocal<>();

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection == null) {
            connection = C3P0Util.getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }

    /**
     * 开启事务
     */
    public static void start() throws SQLException {
        getConnection().setAutoCommit(false);
    }

    /**
     * 提交
     */
    public static void commit() throws SQLException {
        getConnection().commit();
    }

    /**
     * 回滚
     */
    public static void rollBack() throws SQLException {
        getConnection().rollback();
    }

    public static void close() throws SQLException {
        getConnection().close();
    }



}
