package AtmApp.Model.Accounts;

import AtmApp.Model.Transaction.Transaction;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


@Document(collection = "Account")
public abstract class Account {

    @Id
    private String accountId;
    @Indexed(unique = true)
    private double balance = 0;
    private long accountNumber;
    private final LocalDate creationDate;
    private LocalDate nextMonthlyDate;
    private ArrayList<Transaction> transactions = new ArrayList<>();


    public Account(long accountNumber) {
        this.accountNumber = accountNumber;
        this.creationDate = LocalDate.now();
        this.nextMonthlyDate = creationDate.plusMonths(1);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {this.balance = balance;}

    //for database purposes
    public String getAccountId() {
        return accountId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public abstract String getAccountType();

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getNextMonthlyDate() {
        return nextMonthlyDate;
    }

    public void setNextMonthlyDate() {
        nextMonthlyDate = nextMonthlyDate.plusMonths(1);
    }

    public ArrayList<Transaction> getAllTransactions() {
        return transactions;
    }

    public void recordTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public Transaction getRecentTransaction() {
        return transactions.get(transactions.size() - 1);
    }

    //TODO decide if we need this
    @Override
    public String toString() {
        return getAccountType() + " (" + accountNumber + ") ";
    }


    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }


    public abstract double displayBalance();



}
