package AtmApp.Service;

import AtmApp.Model.Accounts.Asset;
import AtmApp.Model.Accounts.RegisteredInvestmentAccount;
import AtmApp.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisteredInvestmentService{
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    InvestmentAccountService investmentAccountService;
    public void deposit(long accountNumber, Asset asset){
        RegisteredInvestmentAccount account = (RegisteredInvestmentAccount)
                accountRepository.findAccountByAccountNumber(accountNumber);

        if(account.checkLimit(asset.getCurrentValue())){
            investmentAccountService.deposit(accountNumber, asset);
            double limit = account.getLimit();
            account.setLimit((int)(limit - asset.getCurrentValue()));
        }

        accountRepository.findAccountByAccountNumber(accountNumber);
    }
}
