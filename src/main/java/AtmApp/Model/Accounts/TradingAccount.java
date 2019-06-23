package AtmApp.Model.Accounts;

import AtmApp.Model.Exceptions.maximumWithdrawException;
import AtmApp.Model.Transaction.Transaction;
import AtmApp.Model.Transaction.TransactionPlus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "Account")
public abstract class TradingAccount extends Account{

    @Id
    private String accountId;
    private Map<String, Asset> portfolio = new HashMap<>();

    public TradingAccount(long id) {
        super(id);
    }

    public Map<String, Asset> getPortfolio() {
        return portfolio;
    }

    public double displayBalance() {
        return super.getBalance();
    }

    public abstract boolean checkFunds(double amount);

    public double getNetProfits() {

        double profits = 0;

        for (Asset asset : portfolio.values()) {
            profits += asset.getProfit();
        }
        return profits;
    }

    public double getTotalEquity() {
        return (getBalance() + getNetProfits());
    }

}

