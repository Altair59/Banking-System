package AtmApp.Model.Users;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class BankTeller extends Client{
    public BankTeller(String username, String email) {
        super(username, email);
        setUserType("Bank Teller");
    }
}
