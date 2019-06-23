package AtmApp.View;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.stereotype.Service;

@Service
public class UserRequestForm extends FormLayout {
    TextField userName;
    TextField password;
    TextField email;
    TextField userType;
    Button create;
    Button signUp;
    Button back;

    public UserRequestForm() {
        back = new Button("login");
        signUp = new Button("Send Request");
        email = new TextField("Email");
        password = new TextField("Password");
        userType = new TextField("User Type");
        userName = new TextField("Username");
        create = new Button("Create");

    }

    public void addUserRequestElements() {
        add(userName, email, userType, create);
    }

    public void addSignUpElements() {
        add(userName, password, email, userType, back, signUp);

    }


}
