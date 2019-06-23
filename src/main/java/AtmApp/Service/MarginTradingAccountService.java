package AtmApp.Service;

import AtmApp.Model.Accounts.Asset;
import AtmApp.Model.Accounts.MarginTradingAccount;
import AtmApp.Model.Accounts.TradingAccount;
import AtmApp.Model.Transaction.TransactionTrade;
import AtmApp.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarginTradingAccountService extends CashTradingAccountService{

    @Autowired
    TradingAccountService tradingAccountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    public void buy(long accountNumber, Asset asset) throws Exception{
        MarginTradingAccount marginTradingAccount = (MarginTradingAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        if (marginTradingAccount.checkFunds(asset.getCurrentValue())){
            asset.setBuyingPrice();
            double margin = marginTradingAccount.getMargin();
            accountService.changeBalance(accountNumber, -(asset.getCurrentValue()*margin));
            tradingAccountService.addAsset(accountNumber, asset);
            double marginBalance = marginTradingAccount.getMarginBalance();
            marginTradingAccount.setMarginBalance(marginBalance + asset.getCurrentValue()*(1-margin));
            marginTradingAccount.recordTransaction(new TransactionTrade(accountNumber, accountNumber,
                    "buy", asset.getBuyingPrice(), asset.getName(), asset.getProfit()));
        }
        accountRepository.save(marginTradingAccount);
    }

    public void sell(long accountNumber, Asset asset, double ratio) throws Exception{
        MarginTradingAccount marginTradingAccount = (MarginTradingAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        super.sell(accountNumber, asset, ratio);
        double margin = marginTradingAccount.getMargin();
        double marginBalance = marginTradingAccount.getMarginBalance();
        marginTradingAccount.setMarginBalance(marginBalance - asset.getBuyingPrice()* (1-margin)*ratio);
        marginTradingAccount.recordTransaction(new TransactionTrade(accountNumber, accountNumber,
                "sell", asset.getCurrentValue(), asset.getName(), asset.getProfit()));

        accountRepository.save(marginTradingAccount);
    }
}
