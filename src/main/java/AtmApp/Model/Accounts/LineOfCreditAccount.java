package AtmApp.Model.Accounts;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "Account")
public class LineOfCreditAccount extends RevolvingLoanAccount{
    private String accountType = "Line of Credit";
    private double interestRate = 0.025;

    public LineOfCreditAccount(long id,int limit){
        super(id,limit);
    }

    public boolean checkFunds(double amount){
        return ((-amount + getBalance()) <= -getLimit());
    }

    @Override
    public String getAccountType() {
        return accountType;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

    public void update(LocalDate date) {
        //Future changes: Monthly statements and daily interest
    }
}
