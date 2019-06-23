package AtmApp.View;

import AtmApp.Model.Accounts.*;
import AtmApp.Model.Users.Client;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.stereotype.Service;

@Service
public class TransactionButtons extends VerticalLayout {

    Button transferButton;
    Button withdrawButton;
    Button depositButton;
    Button checkBalance;


    public TransactionButtons() {
        transferButton = new Button("Transfer");
        withdrawButton = new Button("Withdraw");
        depositButton = new Button("Deposit");
        checkBalance = new Button("CheckBalance");
        add(this.transferButton);
        add(this.withdrawButton);
        add(this.depositButton);
        add(this.checkBalance);
    }

    public void setButtons(Account account){
        if (account instanceof CreditCardAccount){
            remove(depositButton, withdrawButton, transferButton);
        }
    }

}