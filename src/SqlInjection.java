import hierarchyDemo.util.SqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 学习一下 预防 sql 注入的问题
 */
public class SqlInjection {

    public void login(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            SqlUtil.dateBaseName = "test";
            connection = SqlUtil.getConnection();
            String loginSql = "select * from users where user_name = ? and user_password = ?";
            preparedStatement = connection.prepareStatement(loginSql);
            preparedStatement.setObject(1, "张三");
            preparedStatement.setObject(2, "123' or '1' = '1");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("登陆成功");
            } else {
                System.out.println("登陆失败");
            }


        } catch (Exception e) {

        }
    }
}
