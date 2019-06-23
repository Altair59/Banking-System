package AtmApp.Service;

import AtmApp.Model.Atm.AtmAlert;
import AtmApp.Model.Atm.Cash;
import AtmApp.Repositories.AtmAlertRepository;
import AtmApp.Repositories.CashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CashService {
    @Autowired
    private CashRepository cashRepository;

    @Autowired
    private AtmAlertRepository alertRepository;

    private void checkCashSupply(Cash cash, String cashId) throws IOException {
        int[] stock = cash.getStock();
        AtmAlert atmAlert = new AtmAlert();
        if (stock[0] < 20) {
            atmAlert.setBill5(true);
        }
        if (stock[1] < 20) {
            atmAlert.setBill10(true);
        }
        if (stock[2] < 20) {
            atmAlert.setBill20(true);
        }
        if (stock[3] < 20) {
            atmAlert.setBill50(true);
        }
        alertRepository.save(atmAlert);
    }

    public void withdraw(int[] bills, String cashId){
        try {
            Cash cash = cashRepository.findCashByCashId(cashId);
            cash.removeFives(bills[0]);
            cash.removeTens(bills[1]);
            cash.removeTwenties(bills[2]);
            cash.removeFifties(bills[3]);
            checkCashSupply(cash, cashId);
            cashRepository.save(cash);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void deposit(double amount, String cashId) {
        Cash cash = cashRepository.findCashByCashId(cashId);
        cash.setDepositedCash(cash.getDepositedCash()+amount);
        cashRepository.save(cash);
    }

    public Cash getCash(String cashId) {
        return cashRepository.findCashByCashId(cashId);
    }
}
