package AtmApp.Model.Requests;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AccountRequest")
public class JointAccountRequest extends AccountRequest{
    private final String otherUser;
    private final long accountNumber;

    public JointAccountRequest(String username, String accountType, long accountNumber, String otherUser) {
        super(username, accountType);
        this.otherUser = otherUser;
        this.accountNumber = accountNumber;
    }

    public String getOtherUser() { return otherUser; }

    public long getAccountNumber() {
        return accountNumber;
    }
}
