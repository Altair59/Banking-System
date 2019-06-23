package AtmApp.Service;

import AtmApp.Model.Accounts.Asset;
import AtmApp.Model.Accounts.TradingAccount;
import AtmApp.Model.Exceptions.maximumWithdrawException;
import AtmApp.Model.Transaction.Transaction;
import AtmApp.Model.Transaction.TransactionPlus;
import AtmApp.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public abstract class TradingAccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    public void addAsset(long accountNumber, Asset asset) {
        TradingAccount account = (TradingAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        Map<String, Asset> portfolio = account.getPortfolio();
        if (portfolio.containsKey(asset.getName())) {
            //updates the asset in portfolio
            portfolio.get(asset.getName()).addAsset(asset);
        } else {
            portfolio.put(asset.getName(), asset);
        }
        account.recordTransaction(new TransactionPlus(accountNumber,accountNumber,
                "deposit asset", asset.getCurrentValue(), asset.getName()));

        accountRepository.save(account);
    }

    public void deductAsset(long accountNumber, Asset asset, double ratio) {
        TradingAccount account = (TradingAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        Map<String, Asset> portfolio = account.getPortfolio();
        if(ratio > 1 || ratio <=0) {
            //nothing happens
        }
        else if (ratio == 1) {
            portfolio.remove(asset.getName());
            account.recordTransaction(new TransactionPlus(accountNumber,accountNumber,
                    "withdraw asset ", asset.getCurrentValue(), asset.getName()));
        } else {
            asset.deductAsset(ratio);
            account.recordTransaction(new TransactionPlus(accountNumber,accountNumber,
                    "withdraw asset", asset.getCurrentValue(), asset.getName()));
        }

        accountRepository.save(account);
    }

    public abstract void buy(long accountNumber, Asset asset) throws Exception;

    public abstract void sell(long accountNumber, Asset asset, double ratio) throws Exception;

    public void withdraw(long accountNumber, double amount) throws maximumWithdrawException, Exception{
        TradingAccount account = (TradingAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        if (account.getBalance() >= amount) {
            accountService.changeBalance(accountNumber, -amount);
            account.recordTransaction(new Transaction(accountNumber, accountNumber, "withdraw", amount));
        } else {throw new maximumWithdrawException();}

        accountRepository.save(account);
    }
}
