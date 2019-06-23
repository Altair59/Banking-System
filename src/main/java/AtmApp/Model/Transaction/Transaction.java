package AtmApp.Model.Transaction;

import org.springframework.data.annotation.Id;
import java.time.LocalDate;


public class Transaction {

    @Id
    private String transactionID;

    private final long fromAccountNumber;
    private final long toAccountNumber;
    private final String type;
    private final LocalDate date;
    private final double amount;


    public Transaction(long fromNumber, long toNumber, String type, double amount){
        this.fromAccountNumber = fromNumber;
        this.toAccountNumber = toNumber;
        this.type = type;
        this.date = LocalDate.now();
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID='" + transactionID + '\'' +
                ", fromAccountNumber='" + fromAccountNumber + '\'' +
                ", toAccountNumber='" + toAccountNumber + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getTransactionID() {
        return transactionID;
    }

    public long getFromAccountNumber() {
        return fromAccountNumber;
    }

    public long getToAccountNumber() {
        return toAccountNumber;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }
}
