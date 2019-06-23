package AtmApp.Model.Accounts;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "Account")
public class CreditCardAccount extends RevolvingLoanAccount {
    private double interestRate = 0.19;
    private Date lastDayOfInterest;
    private double overdraft;
    private String accountType = "Credit Card";

    public CreditCardAccount(long id, int limit) {
        super(id, limit);
        overdraft = 0.1 * limit;
    }

    public boolean checkFunds(double amount) {
        return (-amount + getBalance()) <= (-getLimit() - overdraft);
    }

    @Override
    public String getAccountType() {
        return accountType;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

<<<<<<< HEAD

=======
>>>>>>> a703e50b10f19b3b1a21103b1933f04a05add398
    public void update(LocalDate date) {
        //Future changes: Monthly statements
    }
}
