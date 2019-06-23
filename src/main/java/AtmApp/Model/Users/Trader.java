package AtmApp.Model.Users;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class Trader extends Client{
    public Trader(String username, String email) {
        super(username, email);
        setUserType("Trader");
    }
}
