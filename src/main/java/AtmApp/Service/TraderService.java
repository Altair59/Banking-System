package AtmApp.Service;

import AtmApp.Model.Accounts.Account;
import AtmApp.Model.Accounts.TradingAccount;
import AtmApp.Model.Users.Trader;
import AtmApp.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraderService {
    @Autowired
    AccountRepository accountRepository;

    public double getTotalEquity(Trader trader){
        double equity = 0;
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts){
            if (account instanceof TradingAccount){
                if (trader.getAccountNumbers().contains(account.getAccountNumber())) {
                    equity += ((TradingAccount) account).getTotalEquity();
                }
            }
        }
        return equity;
    }

    public double getTotalProfits(Trader trader){
        double profit = 0;

        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts){
            if (account instanceof TradingAccount){
                if (trader.getAccountNumbers().contains(account.getAccountNumber())) {
                    profit += ((TradingAccount) account).getNetProfits();
                }
            }
        }
        return profit;
    }

    public double getTradingBalance(Trader trader){
        double balance = 0;

        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts){
            if (account instanceof TradingAccount){
                if (trader.getAccountNumbers().contains(account.getAccountNumber())) {
                    balance += ((TradingAccount) account).getBalance();
                }
            }
        }
        return balance;
    }
}
