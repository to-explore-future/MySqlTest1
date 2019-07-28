package hierarchyDemo.view;

import hierarchyDemo.service.TransferService;

import java.math.BigDecimal;

public class TransferView {
    private int userIDIn = 3;  //收入账户
    private int userIDOut = 1; //支出账户
    private BigDecimal money = new BigDecimal("1000"); //转账金额

    public void transfer(){
        TransferService transferService = new TransferService();
        transferService.transfer(userIDIn, userIDOut, money);
    }
}
