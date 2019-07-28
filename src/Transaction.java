import hierarchyDemo.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 使用原生的api开启事务
 */
public class Transaction {

    /**
     * 转账功能:使用 native API
     */
    public void transferAccounts() throws Exception {
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //"jdbc:mysql://localhost:3306/" + datebaseName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false"
                , "root", "123456");
        try {
            connection.setAutoCommit(false); //不自动提交
            //张三减少1000
            PreparedStatement preparedStatement = connection.prepareStatement("update accounts set account_money = account_money - ? where account_name = ?");
            preparedStatement.setObject(1, 1000);
            preparedStatement.setObject(2, "张三");
            int row = preparedStatement.executeUpdate();

//            int a = 100 / 0;
            //王五增加1000
            PreparedStatement preparedStatement1 = connection.prepareStatement("update accounts set account_money = account_money + ? where account_name = ?");
            preparedStatement1.setObject(1, 1000);
            preparedStatement1.setObject(2, "王五");
            preparedStatement1.executeUpdate();
            connection.commit(); //在这里统一提交 ，两条sql语句作为一个整体，这就叫做事务，
            System.out.println("转账成功");
        } catch (Exception e) {
            connection.rollback(); //回滚
            System.out.println("转账出错：" + e.toString());
        } finally {
            connection.close();
        }
    }

    /**
     * 使用DBUtils 模拟转账功能
     */
    public void transferAccountsByDBUtils() throws SQLException {
        Connection connection = C3P0Util.getConnection();
        try {
            connection.setAutoCommit(false);
            QueryRunner queryRunner = new QueryRunner();
            int row = queryRunner.execute(connection,
                    "update accounts set account_money = account_money - ? where account_name = ?", 1000, "张三");
//            int a = 1 / 0;
            int row2 = queryRunner.execute(connection,
                    "update accounts set account_money = account_money + ? where account_name = ?", 1000, "王五");
            connection.commit();
            if (row > 0 && row2 > 0) {
                System.out.println("转账成功");
            }
        } catch (Exception e) {
            System.out.println("转账失败");
            connection.rollback();
        } finally {
            connection.close();
        }

    }

}
