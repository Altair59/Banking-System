package AtmApp.Model.Users;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "Users")
@Component
@Scope(value = "prototype")
@Qualifier(value = "employee")
public abstract class Employee extends User {

    public Employee(String username,String email, String userType) {
        super(username, email, userType);
    }
}
