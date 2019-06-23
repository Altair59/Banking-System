package AtmApp.Model.Accounts;

import AtmApp.Model.Transaction.TransactionPlus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "Account")

public class ChequingAccount extends AssetTypeAccount{

    private final String accountType = "Chequing Account";
    private double overdraft = 100;
    private double monthlyFee = 14.95;


    public ChequingAccount(long id){
        super(id);
    }


    @Override
    public boolean checkFunds(double amount){
        return (getBalance() + overdraft) >= amount;
    }

    @Override
    public String getAccountType() {
        return accountType;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public void update(LocalDate date) {
        //Future changes: Monthly statements
    }
}


