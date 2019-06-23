package AtmApp.View;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.stereotype.Service;

@Service
public class LoginView extends FormLayout {


    TextField username;
    PasswordField password;
    Button signin;
    Button signup;



    public LoginView(){
        this.username = new TextField("Username");
        this.password = new PasswordField("Password");
        this.signin = new Button("Log In");
        this.signup = new Button("Sign Up");
        add(username);
        add(password);
        add(signup);
        add(signin);
    }



}