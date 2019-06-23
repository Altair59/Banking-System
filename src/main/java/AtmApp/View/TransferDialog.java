package AtmApp.View;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.NumberField;
import org.springframework.stereotype.Service;

@Service
public class TransferDialog extends Dialog {

    Label label;
    NumberField amountField = new NumberField("Amount");
    NumberField accountNumberField = new NumberField("To Account");
    Button transferButton = new Button("Transfer");
    Text status = new Text("");

    public TransferDialog(){
        label = new Label();
        add(label);
        add(amountField);
        add(accountNumberField);
        add(transferButton);
        add(status);
    }

    public void clearTransferDialog() {
        amountField.clear();
        accountNumberField.clear();
    }

}
