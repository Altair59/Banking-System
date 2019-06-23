package AtmApp.Model.Users;

import AtmApp.Model.Accounts.Account;
import AtmApp.Model.Transaction.Transaction;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;

@Document(collection = "User")
public class Auditor extends User {

    private final HashMap<String, User> auditee;

    public Auditor(String username, String email, HashMap<String, User> clients){
        super(username, email, "Auditor");
        this.auditee = clients;
    }

    public HashMap<String, User> getAuditee() {
        return auditee;
    }
}
