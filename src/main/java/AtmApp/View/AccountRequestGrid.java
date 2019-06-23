package AtmApp.View;

import AtmApp.Model.Requests.AccountRequest;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.stereotype.Service;

@Service
public class AccountRequestGrid {

    Grid<AccountRequest> accountRequestGrid;
    Button save;

    public AccountRequestGrid() {

        accountRequestGrid = new Grid<>();
        accountRequestGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        accountRequestGrid.addColumn(AccountRequest::getUsername).setHeader("Username");
        accountRequestGrid.addColumn(AccountRequest::toString).setHeader("Account Details");


    }

}
