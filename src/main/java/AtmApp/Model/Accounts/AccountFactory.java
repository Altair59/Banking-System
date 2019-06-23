package AtmApp.Model.Accounts;
import AtmApp.Model.Requests.AccountRequest;
import AtmApp.Model.Users.Client;
import AtmApp.Repositories.AccountRepository;
import AtmApp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class AccountFactory {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    public AccountFactory(){

    }

    private long generateAccountNumber() {
        // Create a unique id for the new account.
        long id = (long) Math.ceil(100000000 * Math.random());

        if(uniqueChecker(id)){
            return id;
        }
        else {
            return generateAccountNumber();
        }
    }

    private boolean uniqueChecker(long id){
        for (Account a : accountRepository.findAll()){
            if(a.getAccountNumber() == id){
                return false;
            }
        }
        return true;
    }

    public void createAccount(AccountRequest accountRequest) {
        if (accountRequest != null) {
            Client client = userRepository.findByUsername(accountRequest.getUsername());
            String accountType = accountRequest.getAccountType();
            Account account = null;
            long accountNumber = generateAccountNumber();
            if (accountType.equals("Chequing Account")){
                account = new ChequingAccount(accountNumber);
            } else if (accountType.equals("Savings Account")) {
                account = new SavingsAccount(accountNumber);
            } else if (accountType.equals("Unregistered Investment Account")) {
                account = new InvestmentAccount(accountNumber);
            } else if (accountType.equals("Cash Trading Account")) {
                account = new CashTradingAccount(accountNumber);
            } else if (accountType.equals("Margin Trading Account")) {
                account = new MarginTradingAccount(accountNumber,100);
            } else if (accountType.equals("Line of Credit Account")) {
                account = new CreditCardAccount(accountNumber,10000);
            } else if (accountType.equals("Credit Card Account")){
                account = new CreditCardAccount(accountNumber,10000);
            } else if (accountType.equals("Travel Credit Account")){
                account = new TravelCreditCardAccount(accountNumber,10000);
            } else if (accountType.equals("Lump Sum Loan Account")){
                account = new LumpSumLoanAccount(10000,accountNumber,1000000,100,0.0001);
            }

            client.addAccountNumber(accountNumber);
            if (account != null){
                accountRepository.save(account);
            }
            userRepository.save(client);

        }
    }

}
