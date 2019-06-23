package AtmApp.Service;

import AtmApp.Model.Accounts.ChequingAccount;
import AtmApp.Model.Transaction.Transaction;
import AtmApp.Model.Transaction.TransactionPlus;
import AtmApp.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ChequingAccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    @Transactional
    public void payBill(long accountNumber, String name, double amount) throws Exception{
        ChequingAccount account = (ChequingAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        if (account.checkFunds(amount)){
            accountService.changeBalance(accountNumber, -amount);
            account.recordTransaction(new TransactionPlus(accountNumber, accountNumber,
                    "Pay Bill", amount, name));
        }
        accountRepository.save(account);
    }

    @Transactional
    public void update(long accountNumber, LocalDate date) throws Exception{
        ChequingAccount account = (ChequingAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        if(date.isEqual(account.getNextMonthlyDate())){
            accountService.changeBalance(accountNumber, -14.95);
            account.recordTransaction(new Transaction(accountNumber, accountNumber,
                    "monthly account fee", account.getMonthlyFee()));

        }
        accountRepository.save(account);
    }

}
