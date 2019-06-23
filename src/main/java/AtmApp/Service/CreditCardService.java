package AtmApp.Service;

import AtmApp.Model.Accounts.CreditCardAccount;
import AtmApp.Model.Transaction.Transaction;
import AtmApp.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    public void purchase(long accountNumber, double amount) throws Exception{
        CreditCardAccount account = (CreditCardAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        if (account.checkFunds(amount)){
            accountService.changeBalance(accountNumber, -amount);
            account.recordTransaction(new Transaction(accountNumber, accountNumber,"purchase", amount));
        }
        accountRepository.save(account);
    }
}
