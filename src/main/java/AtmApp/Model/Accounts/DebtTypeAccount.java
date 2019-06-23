package AtmApp.Model.Accounts;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Account")
public abstract class DebtTypeAccount extends Account{
    private final int limit;

    DebtTypeAccount(long id, int limit){
        super(id);
        this.limit = limit;
    }

    public double displayBalance() {
        return ( -super.getBalance());
    }

    int getLimit(){ return limit;}

    public abstract double getInterestRate();
}
