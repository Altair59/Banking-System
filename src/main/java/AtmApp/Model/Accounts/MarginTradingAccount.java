package AtmApp.Model.Accounts;

import AtmApp.Model.Transaction.TransactionTrade;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Account")
public class MarginTradingAccount extends CashTradingAccount{
    private int leverage;
    private final double margin = 1/leverage;
    private double rate;
    private double marginBalance = 0;
    private String accountType = "Margin Trading Account";

    public MarginTradingAccount(long id, int leverage){
        super(id);
        this.leverage = leverage;
    }

    public boolean checkFunds(double amount){
        return (amount/leverage) <= getTotalEquity();
        //TODO implement not enough funds error

    }

    public double getMargin() {
        return margin;
    }

    public double getMarginBalance() {
        return marginBalance;
    }

    public void setMarginBalance(double marginBalance) {
        this.marginBalance = marginBalance;
    }

    public void setLeverage(int leverage){this.leverage = leverage;}

    @Override
    public String getAccountType() {
        return accountType;
    }
}
