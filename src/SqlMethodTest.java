import hierarchyDemo.util.SqlUtil;

import java.sql.Connection;
import java.sql.Statement;

public class SqlMethodTest {

    public void init() {
        SqlUtil.dateBaseName = "test";
    }

    public void insert(){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = SqlUtil.getConnection();
            statement = connection.createStatement();
            String insert = "insert into students (student_name) values ('张三');";
            int row = statement.executeUpdate(insert);
            System.out.println("成功在第" + row + "行插入数据");
        } catch (Exception e) {
            System.out.println(e.toString());
        }finally {
            SqlUtil.closeAll(connection, null, statement);
        }
    }





    public void update(){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = SqlUtil.getConnection();
            statement = connection.createStatement();
            String update = "update students set student_name = '李四' where student_name = '张三';";
            int row = statement.executeUpdate(update);
            System.out.println("成功在第" + row + "行更新数据");
        } catch (Exception e) {
            System.out.println(e.toString());
        }finally {
            SqlUtil.closeAll(connection, null, statement);
        }
    }

    public void delete(){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = SqlUtil.getConnection();
            statement = connection.createStatement();
            String delete = "delete from students where student_name = '李四';";
            int row = statement.executeUpdate(delete);
            System.out.println("成功在第" + row + "行删除数据");
        } catch (Exception e) {
            System.out.println(e.toString());
        }finally {
            SqlUtil.closeAll(connection, null, statement);
        }
    }

    public void query(){

    }

}
