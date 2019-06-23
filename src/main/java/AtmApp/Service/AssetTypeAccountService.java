package AtmApp.Service;

import AtmApp.Model.Accounts.Account;
import AtmApp.Model.Accounts.Asset;
import AtmApp.Model.Accounts.AssetTypeAccount;
import AtmApp.Model.Transaction.Transaction;
import AtmApp.Model.Transaction.TransactionPlus;
import AtmApp.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class AssetTypeAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    @Transactional
    public void withdraw(long accountNumber, double amount) throws Exception{
        AssetTypeAccount account = (AssetTypeAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        if(account.checkFunds(amount)){
            accountService.changeBalance(accountNumber, -amount);
            account.recordTransaction(new Transaction(account.getAccountNumber(), account.getAccountNumber(),
                    "withdraw", amount));
        }
        accountRepository.save(account);
    }

    @Transactional
    public void transfer(long accountNumber, double amount, long destination) throws Exception{
        AssetTypeAccount account = (AssetTypeAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        if(account.checkFunds(amount)){

            accountService.changeBalance(accountNumber, -amount);
            account.recordTransaction(new TransactionPlus(account.getAccountNumber(), destination,
                    "transfer",amount, Long.toString(destination)));
            accountRepository.save(account);
            accountService.deposit(amount, destination);

        }
    }

}
