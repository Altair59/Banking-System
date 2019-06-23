package AtmApp.Model.Accounts;

import AtmApp.Model.Transaction.Transaction;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashMap;

@Document(collection = "Account")
public class InvestmentAccount extends AssetTypeAccount {

    private final HashMap<String, Asset> portfolio = new HashMap<String, Asset>();
    private String accountType = "Unregistered Investment Account";

    public InvestmentAccount(long id){
        super(id);
    }

    @Override
    public String getAccountType() {
        return accountType;
    }

    public boolean checkFunds(double amount){
        return getBalance() >= amount;
    }

    //returns the total profit of the account
    public double getAssetAcccountValue(){
        double value = 0;
        for (Asset asset : portfolio.values()) {
            value += asset.getProfit();
        }
        return value;
    }

    public HashMap<String, Asset> getPortfolio() {
        return portfolio;
    }

    public void update(LocalDate date) {
        //Future changes: Monthly statements and daily interest
    }

    public static void main(String[] args) {

    }
}

