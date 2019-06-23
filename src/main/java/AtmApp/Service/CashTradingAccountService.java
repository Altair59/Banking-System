package AtmApp.Service;

import AtmApp.Model.Accounts.Account;
import AtmApp.Model.Accounts.Asset;
import AtmApp.Model.Accounts.CashTradingAccount;
import AtmApp.Model.Accounts.TradingAccount;
import AtmApp.Model.Transaction.TransactionTrade;
import AtmApp.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CashTradingAccountService extends TradingAccountService{

    @Autowired
    TradingAccountService tradingAccountService;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    public void buy(long accountNumber, Asset asset) throws Exception {
        CashTradingAccount account = (CashTradingAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        if (account.checkFunds(asset.getCurrentValue())) {
            asset.setBuyingPrice();
            accountService.changeBalance(accountNumber, -asset.getCurrentValue());
            tradingAccountService.addAsset(accountNumber, asset);
            account.recordTransaction(new TransactionTrade(account.getAccountNumber(), account.getAccountNumber(),
                    "buy", asset.getBuyingPrice(), asset.getName(), asset.getProfit()));
        }
        accountRepository.save(account);
    }

    public void sell(long accountNumber, Asset asset, double ratio) throws Exception{
        CashTradingAccount account = (CashTradingAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        accountService.changeBalance(accountNumber,asset.getCurrentValue()*ratio);
        tradingAccountService.deductAsset(accountNumber, asset, ratio);
        account.recordTransaction(new TransactionTrade(account.getAccountNumber(), account.getAccountNumber(),
                "sell", asset.getCurrentValue(), asset.getName(), asset.getProfit()));

        accountRepository.save(account);
    }

}