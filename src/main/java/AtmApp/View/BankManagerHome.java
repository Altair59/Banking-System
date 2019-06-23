package AtmApp.View;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.button.Button;
import org.springframework.stereotype.Service;

@Service
public class BankManagerHome extends AppLayout {

    AppLayoutMenu appLayoutMenu;
    Button createUser;
    Button createAccount;
    Button reloadAtm;
    Button undoTransactions;
    Button signout;


    public BankManagerHome() {
        signout = new Button("Sign out");
        undoTransactions = new Button("Undo Transactions");
        appLayoutMenu = this.createMenu();
        createUser = new Button("Create User");
        createAccount = new Button("Create Account");
        reloadAtm = new Button("Reload Atm");
        appLayoutMenu.addMenuItem(createUser);
        appLayoutMenu.addMenuItem(createAccount);
        appLayoutMenu.addMenuItem(reloadAtm);
        appLayoutMenu.addMenuItem(signout);
    }


}
