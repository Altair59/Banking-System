package AtmApp.Model.Users;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "Users")
@Component
@Scope(value = "prototype")
@Qualifier(value = "employee")
public class BankManager extends Employee {
    public BankManager(String username, String email) {
        super(username, email, "Bank Manager");
    }
}
