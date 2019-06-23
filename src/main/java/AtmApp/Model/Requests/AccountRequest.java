package AtmApp.Model.Requests;

import AtmApp.Model.Accounts.Account;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AccountRequest")
public class AccountRequest {

    @Id
    private String accountRequestId;
    private final String accountType;
    private final String username;

    public AccountRequest(String accountType, String username) {
        this.username = username;
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "AccountRequest : " +
                "accountRequestId= '" + accountRequestId + '\'' +
                ", account = " + accountType +
                ", username = '" + username + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public String getAccountType() {
        return accountType;
    }
}
