package AtmApp.View;

import AtmApp.Model.Requests.UserRequest;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.stereotype.Service;

@Service
public class UserRequestGrid {

    Grid<UserRequest> userRequestGrid;


    public UserRequestGrid() {
        userRequestGrid = new Grid<>();
        userRequestGrid.addColumn(UserRequest::getUsername).setHeader("Username");
        userRequestGrid.addColumn(UserRequest::getPassword).setHeader("Password");
        userRequestGrid.addColumn(UserRequest::getEmail).setHeader("Email");
        userRequestGrid.addColumn(UserRequest::getUserType).setHeader("User Type");
        userRequestGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
    }


}
