package AtmApp.Service;

import AtmApp.Model.Accounts.Asset;
import AtmApp.Model.Accounts.InvestmentAccount;
import AtmApp.Model.Transaction.Transaction;
import AtmApp.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InvestmentAccountService {
    @Autowired
    AccountRepository accountRepository;

    public void deposit(long accountNumber, Asset asset) {
        InvestmentAccount account = (InvestmentAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        Map<String, Asset> portfolio = account.getPortfolio();
        if (portfolio.containsKey(asset.getName())) {
            //updates the asset in portfolio
            portfolio.get(asset.getName()).addAsset(asset);
        } else {
            portfolio.put(asset.getName(), asset);
        }
        account.recordTransaction(new Transaction(accountNumber, accountNumber,
                "deposit", asset.getCurrentValue()));

        accountRepository.save(account);
    }
}
