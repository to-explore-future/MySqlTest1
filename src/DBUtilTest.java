import bean.UsersBean;
import hierarchyDemo.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 使用dbutils
 */
class DBUtilTest {

    void insert() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        int row = queryRunner.update("insert into users (user_name,user_password) values (?,?)", "张三", "123");
        System.out.println("dbutils:insert()" + row);
    }

    void update() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        int row = queryRunner.update("update users set user_password = ? where user_name = ?", "abc", "张三");
        System.out.println("dbutils:update()" + row);
    }

    void delete() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        int row = queryRunner.update("delete from users where user_name = ?", "张三");
        System.out.println("dbutils:delete()" + row);
    }

    void query() throws SQLException {
        arrayHandlerQuery();
        arrayListHandlerQuery();
        beanHandlerQuery();
        beanListHandlerQuery();
        columnListHandlerQuery();
        mapListHandlerQuery();
        scalarHandlerQuery();

    }

    /**
     * 把查到的结果的第一行，中的每个字段的值保存到Object[] 数组中。
     */
    private void arrayHandlerQuery() throws SQLException {
        System.out.println("arrayHandlerQuery()");
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        Object[] objects = queryRunner.query("select * from users", new ArrayHandler());
        for (Object obj : objects) {
            System.out.print(obj + "\t");
        }
    }

    /**
     * 把查询到的结果保存到一个ArrayList中
     */
    private void arrayListHandlerQuery() throws SQLException {
        System.out.println("arrayListHandlerQuery()");
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        List<Object[]> queryResult = queryRunner.query("select * from users", new ArrayListHandler());
        for (Object[] objects : queryResult) {
            for (Object object : objects) {
                System.out.print(object + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 把查询到结果封装到第一条结果封装到 bean 中，注意：bean一定要生成 get set方法，否则无法赋值。
     */
    private void beanHandlerQuery() throws SQLException {
        System.out.println("arrayListHandlerQuery()");
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        UsersBean users = queryRunner.query("select * from users", new BeanHandler<>(UsersBean.class));
        System.out.print(users.toString() + "\t\n" );
    }

    /**
     * 把查询到的结果全部赋值到list集合中
     */
    private void beanListHandlerQuery() throws SQLException {
        System.out.println("beanListHandlerQuery()");
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        List<UsersBean> usersBeans = queryRunner.query("select * from users", new BeanListHandler<>(UsersBean.class));
        for (UsersBean usersBean : usersBeans) {
            System.out.println(usersBean.toString());
        }
        System.out.println();
    }

    /**
     * 把查询到的所有的结果中的某一列全部取出来，放到一个集合中。
     */
    private void columnListHandlerQuery() throws SQLException {
        System.out.println("columnListHandlerQuery()");
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        List<String> user_name = queryRunner.query("select * from users", new ColumnListHandler<>("user_name"));
        System.out.println(user_name);
    }

    /**
     * 把查询到的每一条封装成一个map，然后再装到一个list中。
     */
    private void mapListHandlerQuery() throws SQLException {
        System.out.println("mapListHandlerQuery()");
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        List<Map<String, Object>> mapList = queryRunner.query("select * from users", new MapListHandler());
        System.out.println(mapList);
    }

    /**
     * 当查询的结果是单一的值时，这时候使用scalarHandler，可以指定泛型
     */
    private void scalarHandlerQuery() throws SQLException {
        System.out.println("scalarHandlerQuery()");
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
        Object scalar = queryRunner.query("select count(*) from users", new ScalarHandler<>());
        System.out.println(scalar);
    }

}
