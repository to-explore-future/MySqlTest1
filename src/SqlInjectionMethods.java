import hierarchyDemo.util.C3P0Util;
import hierarchyDemo.util.SqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 使用方式sql注入的方式，把增删改查方法练习一遍
 */
public class SqlInjectionMethods {

    public void insert(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = C3P0Util.getConnection();
            String insertSql = "insert into users (user_name,user_password)values (?,?)";
            preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setObject(1, "test");
            preparedStatement.setObject(2, "test");
            int row = preparedStatement.executeUpdate();
            System.out.println("在第" + row + "行插入成功");
        } catch (Exception e) {
            System.out.println(e.toString());
        }finally {
            SqlUtil.closeAll(connection,null,preparedStatement);
        }
    }

    public void update(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = C3P0Util.getConnection();
            String insertSql = "update users set user_password = ? where user_name = ? and user_password = ?";
            preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setObject(1, "123");
            preparedStatement.setObject(2, "test");
            preparedStatement.setObject(3, "test");
            int row = preparedStatement.executeUpdate();
            System.out.println("密码更新成功");
        } catch (Exception e) {
            System.out.println(e.toString());
        }finally {
            SqlUtil.closeAll(connection,null,preparedStatement);
        }
    }

    public void delete(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = C3P0Util.getConnection();
            String insertSql = "delete from users where user_name = ? and user_password = ?";
            preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setObject(1, "test");
            preparedStatement.setObject(2, "123");
            int row = preparedStatement.executeUpdate();
            System.out.println("账号删除成功");
        } catch (Exception e) {
            System.out.println(e.toString());
        }finally {
            SqlUtil.closeAll(connection,null,preparedStatement);
        }
    }

    public void query(){

    }
}
