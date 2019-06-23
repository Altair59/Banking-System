package AtmApp.Model.Accounts;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Account")
public class RegisteredInvestmentAccount extends InvestmentAccount {

    private int limit;
    private int term;
    private String accountType = "Registered Investment Account";

    public RegisteredInvestmentAccount(int limit, int term, long id){
        super(id);
    }

    public void setLimit(int limit){this.limit = limit;}
    public int getLimit() { return limit;}

    public boolean checkLimit(double amount) {
        return (limit - amount) >= 0;
    }

    @Override
    public String getAccountType() {
        return accountType;
    }
}

