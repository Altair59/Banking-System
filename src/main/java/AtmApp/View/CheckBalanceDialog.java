package AtmApp.View;

import AtmApp.Model.Transaction.Transaction;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.stereotype.Service;


@Service
public class CheckBalanceDialog extends Dialog {
    TextField balance;
    Grid<Transaction> transactionGrid;

    public CheckBalanceDialog() {
        balance = new TextField("Balance");
        balance.isReadOnly();
        transactionGrid = new Grid<>();
        transactionGrid.addColumn(Transaction::getFromAccountNumber).setHeader("From Account");
        transactionGrid.addColumn(Transaction::getToAccountNumber).setHeader("To Account");
        transactionGrid.addColumn(Transaction::getAmount).setHeader("Amount");
        transactionGrid.addColumn(Transaction::getDate).setHeader("Initiated on");
        transactionGrid.addColumn(Transaction::getType).setHeader("Transaction type");
        add(balance);
        add(transactionGrid);
        setWidth("800px");

    }
}



