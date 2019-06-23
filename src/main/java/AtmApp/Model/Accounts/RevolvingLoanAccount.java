package AtmApp.Model.Accounts;
import AtmApp.Model.Exceptions.reachedLimitException;
import AtmApp.Model.Transaction.Transaction;
import AtmApp.Model.Transaction.TransactionPlus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "Account")
public abstract class RevolvingLoanAccount extends DebtTypeAccount{
    RevolvingLoanAccount(long id, int limit){
        super(id, limit);
    }

    public abstract boolean checkFunds(double amount);

}
