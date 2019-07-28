import hierarchyDemo.util.SqlUtil;
import hierarchyDemo.view.TransferView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws Exception{
//        simpleTest();

        SqlMethodTest sqlMethodTest = new SqlMethodTest();
        sqlMethodTest.init();
        sqlMethodTest.insert();
        sqlMethodTest.update();
        sqlMethodTest.delete();

        new SqlInjection().login();

        SqlInjectionMethods sqlInjectionMethods = new SqlInjectionMethods();
        sqlInjectionMethods.insert();
        sqlInjectionMethods.update();
        sqlInjectionMethods.delete();

        DBUtilTest dbUtilTest = new DBUtilTest();
        dbUtilTest.insert();
        dbUtilTest.update();
        dbUtilTest.delete();
        dbUtilTest.query();

        Transaction transaction = new Transaction();
//        transaction.transferAccounts();
        transaction.transferAccountsByDBUtils();

        System.out.println("\n\n\n");
        TransferView transferView = new TransferView();
        transferView.transfer();

    }

    private static void simpleTest() throws Exception {
        Connection connection = SqlUtil.getConnection();
        Statement statement = connection.createStatement();
        String selectTest = "select * from country";
        ResultSet resultSet = statement.executeQuery(selectTest);
        while (resultSet.next()) {
            Object name = resultSet.getObject("Name");
            System.out.print(name + "\t");
        }
        SqlUtil.closeAll(connection,resultSet,statement);
    }
}
