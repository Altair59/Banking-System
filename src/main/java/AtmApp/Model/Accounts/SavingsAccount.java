package AtmApp.Model.Accounts;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "Account")
public class SavingsAccount extends AssetTypeAccount {
    private String accountType = "Savings Account";
    private final double interestRate;

    public SavingsAccount(long id) {
        super(id);
        this.interestRate = 0.001;
    }

    public boolean checkFunds(double amount) {
        return amount <= getBalance();
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public String getAccountType() {
        return accountType;
    }

    public void update(LocalDate date) {
        //Future changes: Monthly statements and daily interest
    }
}
