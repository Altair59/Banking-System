package AtmApp.Model.Accounts;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection="Account")
public class LumpSumLoanAccount extends DebtTypeAccount{

    private final double interestRate ;
    private final double monthlyPayment;
    private final double initialLoan;
    private String accountType = "Personal Loans";

    public LumpSumLoanAccount(int limit, long id, double loan, int term, double rate){
        super(id, limit);
        this.initialLoan = loan;
        this.interestRate = rate;
        int loanTerm = term;
        this.monthlyPayment = (loan*rate)/(term*12);
        setBalance(-loan);
    }


    public double getMonthlyPayment(){return monthlyPayment;}

    public String getAccountType() {
        return accountType;
    }

    public double getInterestRate(){ return interestRate;}

    public void update(LocalDate date) {
        //Future changes: Monthly statements and daily interest
    }

}

