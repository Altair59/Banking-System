package AtmApp.Service;

import AtmApp.Model.Accounts.LumpSumLoanAccount;
import AtmApp.Model.Transaction.Transaction;
import AtmApp.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LumpSumLoanService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    public void deposit(long accountNumber, double amount) throws Exception{
        LumpSumLoanAccount account = (LumpSumLoanAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        accountService.changeBalance(accountNumber, amount);
        account.recordTransaction(new Transaction(accountNumber, accountNumber, "payment", amount));
        accountRepository.save(account);
    }

    public void update(long accountNumber, LocalDate date){
        LumpSumLoanAccount account = (LumpSumLoanAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        account.setNextMonthlyDate();
        accountRepository.save(account);

        //future feature includes sending a monthly statement
    }
}
