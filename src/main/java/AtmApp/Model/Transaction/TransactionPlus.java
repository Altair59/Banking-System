package AtmApp.Model.Transaction;

public class TransactionPlus extends Transaction {

    private final String asset;

    // Transactions for transfers and payBills and deposit assets or transactions with 5 components
    // Transaction format: Date, Account ID, transaction, amount, asset name or destination
    // Transaction Example: 2007-12-03 123456 deposit Asset $300 Bond

    public TransactionPlus(long fromNumber, long toNumber, String type, double amount, String asset){

        super(fromNumber, toNumber, type, amount);
        this.asset = asset;
    }

    public String toString(){
        return "Transaction{" +
                "transactionID='" + getTransactionID()+ '\'' +
                ", fromAccountNumber='" + getFromAccountNumber() + '\'' +
                ", toAccountNumber='" + getToAccountNumber() + '\'' +
                ", amount=" + getAmount() +
                ", asset=" + asset +
                ", type='" + getType() + '\'' +
                ", date='" + getDate() + '\'' +
                '}';
    }
}
