package AtmApp.Service;

import AtmApp.Model.Accounts.RevolvingLoanAccount;
import AtmApp.Model.Exceptions.reachedLimitException;
import AtmApp.Model.Transaction.Transaction;
import AtmApp.Model.Transaction.TransactionPlus;
import AtmApp.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RevolvingLoanAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    public void withdraw(long accountNumber, double amount) throws reachedLimitException, Exception {
        RevolvingLoanAccount account = (RevolvingLoanAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        if (account.checkFunds(amount)) {
            accountService.changeBalance(accountNumber, -amount);
            account.recordTransaction(new Transaction(accountNumber, accountNumber,
                    "withdraw", amount));
        }
        else {throw new reachedLimitException();}

        accountRepository.save(account);
    }

    public void payBill(long accountNumber, String name, double amount) throws Exception{
        RevolvingLoanAccount account = (RevolvingLoanAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        if (account.checkFunds(amount)){
            accountService.changeBalance(accountNumber, -amount);
            account.recordTransaction(new TransactionPlus(accountNumber, accountNumber,
                    "Pay Bill", amount, name));
        }

        accountRepository.save(account);
    }

    public void applyInterest(long accountNumber) throws Exception{
        RevolvingLoanAccount account = (RevolvingLoanAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        if (account.getBalance()<0) {
            double interest = account.getBalance() * account.getInterestRate();
            accountService.changeBalance(accountNumber, interest);
            account.recordTransaction(new Transaction(accountNumber, accountNumber,"interest charged", -interest));
        }
        accountRepository.save(account);
    }

    protected void update(long accountNumber, LocalDate date) throws Exception{
        RevolvingLoanAccount account = (RevolvingLoanAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        if (date.isEqual(account.getNextMonthlyDate())) {
            applyInterest(accountNumber);
            account.setNextMonthlyDate();
        }
        accountRepository.save(account);
    }
}
