package AtmApp.View;

import AtmApp.Model.Accounts.Account;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRequestDialog extends Dialog {

    ComboBox<String> accountComboBox;
    Button send;
    Label status;

    public AccountRequestDialog() {
        status = new Label();
        accountComboBox = new ComboBox<String>();
        send = new Button("Send");
        add(status);
        add(accountComboBox);
        add(send);
    }

    public void setAccountComboBox(List<String> accounts) {
        accountComboBox.setItems(accounts);
    }

    public void clearAll() {
        accountComboBox.clear();
        status.setText("");
    }

}
