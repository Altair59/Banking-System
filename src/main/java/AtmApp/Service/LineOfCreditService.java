package AtmApp.Service;

import AtmApp.Model.Accounts.Account;
import AtmApp.Model.Accounts.LineOfCreditAccount;
import AtmApp.Model.Transaction.TransactionPlus;
import AtmApp.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineOfCreditService{
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    public void transfer(long accountNumber, double amount, long destination) throws Exception{
        LineOfCreditAccount account = (LineOfCreditAccount) accountRepository.findAccountByAccountNumber(accountNumber);

        if (account.checkFunds(amount)){
            accountService.changeBalance(accountNumber, -amount);
            account.recordTransaction(new TransactionPlus(accountNumber, destination,
                    "transfer",amount, Long.toString(destination)));
            accountService.deposit(amount, destination);
        }
        accountRepository.save(account);
    }
}
