package AtmApp.Service;

import AtmApp.Model.Accounts.TravelCreditCardAccount;
import AtmApp.Model.Requests.AccountRequest;
import AtmApp.Model.Transaction.Transaction;
import AtmApp.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TravelCreditCardService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    private void addPoints(long accountNumber, double amount){
        TravelCreditCardAccount account = (TravelCreditCardAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        account.setPoints(account.getPoints() + (int) amount*100);
        accountRepository.save(account);
    }

    public void redeemPoints(long accountNumber, int amount){
        TravelCreditCardAccount account = (TravelCreditCardAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        account.setPoints(account.getPoints() - amount);
        accountRepository.save(account);
    }

    public void update(long accountNumber, LocalDate date) throws Exception{
        TravelCreditCardAccount account = (TravelCreditCardAccount) accountRepository.findAccountByAccountNumber(accountNumber);
        LocalDate annualDate = account.getNextAnnualFeeDate();
        if (annualDate.isEqual(date)){
            double annualFee = account.getAnnualFee();
            accountService.changeBalance(accountNumber, -annualFee);
            account.recordTransaction(new Transaction(accountNumber, accountNumber, "annual fee", annualFee));
            account.setNextAnnualFeeDate(annualDate.plusYears(1));
        }

    }
}
