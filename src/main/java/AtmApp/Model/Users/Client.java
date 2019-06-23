package AtmApp.Model.Users;

import AtmApp.Model.Transaction.Transaction;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Users")

public class Client extends User {


    private List<Long> accountNumbers = new ArrayList<Long>();
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public Client(String username, String email) {

        super(username, email,"Client");
    }

    private List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Long> getAccountNumbers() {
        return accountNumbers;
    }

    public void addAccountNumber(Long accountNumber) {
        accountNumbers.add(accountNumber);
    }
}
