package AtmApp.Model.Accounts;

import AtmApp.Model.Exceptions.reachedLimitException;
import AtmApp.Model.Transaction.Transaction;

import java.time.LocalDate;

public class TravelCreditCardAccount extends CreditCardAccount {
    private int points = 0;
    private double interestRate = 0.29;
    private LocalDate nextAnnualFeeDate;
    private String accountType = "Travel Credit Card";
    private int annualFee = 99;

    public TravelCreditCardAccount(long id,int limit){

        super(id,limit);
        nextAnnualFeeDate = getCreationDate().plusYears(1);
    }

    @Override
    public String getAccountType() {
        return accountType;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LocalDate getNextAnnualFeeDate() {
        return nextAnnualFeeDate;
    }

    public void setNextAnnualFeeDate(LocalDate nextAnnualFeeDate) {
        this.nextAnnualFeeDate = nextAnnualFeeDate;
    }

    public int getAnnualFee() {
        return annualFee;
    }

    public void setAnnualFee(int annualFee) {
        this.annualFee = annualFee;
    }
}
