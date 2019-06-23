package AtmApp.Model.Transaction;

public class TransactionTrade extends Transaction {

    //Transaction record for buying/selling assets in trading accounts
    //Transaction format: Date, Account ID, transaction, amount, asset name or destination
    // Transaction Example: 2007-12-03 123456 sell $300 Google Stock -$40

    private final String item;
    private final double profit;

    public TransactionTrade(long fromNumber, long toNumber, String type, double amount, String item, double profit){
        super(fromNumber, toNumber,  type,  amount);
        this.item = item;
        this.profit = profit;
    }

    public String toString(){
        return (super.toString() + " " + item + " $" + profit);
    }

}
