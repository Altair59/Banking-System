package AtmApp.Model.Users;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Users")
public class User {

    @Id
    private String userId;
    private String username;
    private String password;
    private String email;
    private String userType;


    public User(String username, String email, String userType) {
        this.username = username;
        this.email = email;
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (checkPassword(oldPassword)) {
            this.password = newPassword;
        }

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean checkPassword(String attempt){return attempt.equals(password);}

    @Override
    public String toString() {
        return username;
    }
}
