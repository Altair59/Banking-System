package AtmApp.Model.Accounts;
import AtmApp.Model.Transaction.Transaction;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Account")
public abstract class AssetTypeAccount extends Account{
    AssetTypeAccount(long id){
        super(id);
    }

    public double displayBalance() {
        return super.getBalance();
    }

    public abstract boolean checkFunds(double amount);
}

