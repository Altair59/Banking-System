package AtmApp.Model.Accounts;

import AtmApp.Model.Transaction.TransactionTrade;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Observable;

@Document(collection = "Account")
public class CashTradingAccount extends TradingAccount{
    private String accountType = "Regular Paying Trading Account";

    public CashTradingAccount(long id) {
        super(id);
    }

    public boolean checkFunds(double value) {
        return value <= getBalance();
        //TODO implement not enough funds error
    }


    @Override
    public String getAccountType() {
        return accountType;
    }

<<<<<<< HEAD
=======

>>>>>>> a703e50b10f19b3b1a21103b1933f04a05add398
    public void update(LocalDate date) {
        //Future changes: Monthly statements and annual tax info
    }
}
