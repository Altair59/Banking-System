package AtmApp.Service;

import AtmApp.Model.Accounts.SavingsAccount;
import AtmApp.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SavingsAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    public void gainInterest(long accountNumber) throws Exception {
        SavingsAccount account = (SavingsAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        accountService.changeBalance(accountNumber, account.getBalance() * account.getInterestRate());
        accountRepository.save(account);
    }

    public void update(long accountNumber, LocalDate date) throws Exception {
        SavingsAccount account = (SavingsAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        if (date.isEqual(account.getNextMonthlyDate())) {
            gainInterest(accountNumber);
        }
        accountRepository.save(account);
    }
}
