package AtmApp.View;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.NumberField;
import org.springframework.stereotype.Service;

@Service
public class AtmDialog extends Dialog {

    NumberField fiftyField;
    NumberField twentyField;
    NumberField tenField;
    NumberField fiveField;
    Button performAction;
    Label status;
    Label balance;

    public AtmDialog() {
        fiftyField = new NumberField("Fifties");
        twentyField = new NumberField("Twenties");
        tenField = new NumberField("Tens");
        fiveField = new NumberField("Fives");
        performAction = new Button();
        status = new Label();
        balance = new Label();

        fiftyField.setValue(1d);
        fiftyField.setHasControls(true);
        twentyField.setValue(1d);
        twentyField.setHasControls(true);
        tenField.setValue(1d);
        tenField.setHasControls(true);
        fiveField.setValue(1d);
        fiveField.setHasControls(true);
        add(balance);
        add(fiftyField, twentyField, tenField, fiveField);
        add(performAction);
        add(status);
    }


}
