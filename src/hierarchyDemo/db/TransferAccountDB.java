package hierarchyDemo.db;


import org.apache.commons.dbutils.QueryRunner;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 转账db
 */
public class TransferAccountDB {

    /**
     * 转入：in
     * @param userID :转入谁的账户，这个账户的钱要增加
     */
    public void transferIn(Connection connection, int userID, String money) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        queryRunner.execute(connection,"update accounts1 set account_money = account_money + ? where account_id = ?", money, userID);
    }

    /**
     * 转出：out
     * @param userID :谁的账户的钱要转出，这个账户的钱要减少
     */
    public void transferOut(Connection connection, int userID, String money) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        queryRunner.execute(connection,"update accounts1 set account_money = account_money - ? where account_id = ?", money, userID);
    }

}
