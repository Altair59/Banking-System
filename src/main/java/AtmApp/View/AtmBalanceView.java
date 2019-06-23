package AtmApp.View;

import AtmApp.Model.Atm.Atm;
import AtmApp.Model.Atm.Cash;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.stereotype.Service;

@Service
public class AtmBalanceView {

    Grid<Cash> atmGrid;

    public AtmBalanceView() {
        atmGrid = new Grid<>();
        atmGrid.addColumn(Cash::getCashId).setHeader("AtmId");
        atmGrid.addColumn(Cash::getFifties).setHeader("50's");
        atmGrid.addColumn(Cash::getTwenties).setHeader("20's");
        atmGrid.addColumn(Cash::getTens).setHeader("10's");
        atmGrid.addColumn(Cash::getFives).setHeader("5's");

    }


}
