package AtmApp.Model.Atm;

import AtmApp.Model.Requests.AccountRequest;
import AtmApp.Model.Requests.JointAccountRequest;
import AtmApp.Model.Transaction.Transaction;
import AtmApp.Model.Users.User;
import AtmApp.Service.currencyTable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.*;

@Document(collection = "Atm")

public class Atm extends Observable{

    @Id
    private String atmId;

    public currencyTable table;
    private HashMap<String, User> users;
    private User user;
    private final Cash cash;
    private boolean loggedIn = false;
    private Queue<AccountRequest> creationRequests = new LinkedList<AccountRequest>();
    private Queue<JointAccountRequest> jointRequests = new LinkedList<JointAccountRequest>();
    private LocalDate currentDate;
    private Queue<Transaction> undoRequests = new LinkedList<Transaction>();
    private List<Transaction> transactions = new ArrayList<Transaction>();


    public Atm() {
        this.currentDate = LocalDate.now();
        table = new currencyTable();
        cash = new Cash();
    }

    public String getAtmId() {
        return atmId;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    public AccountRequest getCreationRequest() {
        return creationRequests.poll();
    }

    public JointAccountRequest getJointRequests() {
        return  jointRequests.poll();
    }

    public Transaction getUndoRequest() {
        return undoRequests.poll();
    }

    public void addRequest(AccountRequest request) {
        creationRequests.add(request);
    }

    public User getCurrentUser() {
        return user;
    }

    public boolean getLoggedIn() { return loggedIn; }

    public void setLoggedIn(boolean b) {loggedIn = b; }

    public void restock(int[] bills) {cash.reStock(bills);}

    public HashMap<String, User> getUsers() {
        return users;
    }

    public Cash getCashManager() {
        return cash;
    }

    public void updateDate(){
        this.currentDate = LocalDate.now();
        setChanged();
        notifyObservers(currentDate);
    }

}
