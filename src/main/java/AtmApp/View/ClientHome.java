package AtmApp.View;

import AtmApp.Model.Accounts.Account;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientHome extends AppLayout {


    ComboBox<Account> accountComboBox;
    AppLayoutMenu appLayoutMenu;
    Button signOut;
    Button accountRequestButton;


    public ClientHome(){
        this.accountRequestButton = new Button("Account Request");
        this.signOut = new Button("Sign out");
        this.setVisible(false);
        accountComboBox = new ComboBox<>();
        accountComboBox.setItemLabelGenerator(Account::toString);
        accountComboBox.setLabel("Accounts");
        this.appLayoutMenu = this.createMenu();
        addMenuItem(accountRequestButton);
        addMenuItem(accountComboBox);
        addMenuItem(signOut);
    }

    public void addMenuItem(Component component){
        this.appLayoutMenu.addMenuItem(component);
    }

    public void setComboBoxItems(List<Account> accountList){
        accountComboBox.setItems(accountList);
    }



}


