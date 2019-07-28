package hierarchyDemo.service;

import hierarchyDemo.db.TransferAccountDB;
import hierarchyDemo.util.ConnectionManager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 转账
 */
public class TransferService {

    public void transfer(int userIDIn, int userIDOut, BigDecimal money) {

        //必要的判断 。。。

        TransferAccountDB transferAccountDB = new TransferAccountDB();
        try {
            Connection connection = ConnectionManager.getConnection();
            ConnectionManager.start();
            transferAccountDB.transferIn(connection, userIDIn, money.toString());
            int a = 10 / 0;
            transferAccountDB.transferOut(connection, userIDOut, money.toString());
            ConnectionManager.commit();
            System.out.println("转账成功");
        } catch (Exception e) {
            try {
                ConnectionManager.rollBack();
                System.out.println("转账失败，已经回滚");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            try {
                ConnectionManager.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
