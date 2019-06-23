package AtmApp.Service;


import AtmApp.Model.Accounts.Account;
import AtmApp.Model.Accounts.ChequingAccount;
import AtmApp.Model.Requests.JointAccountRequest;
import AtmApp.Model.Users.Client;
import AtmApp.Repositories.AccountRepository;
import AtmApp.Repositories.AccountRequestRepository;
import AtmApp.Model.Requests.AccountRequest;
import AtmApp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    AccountRequestRepository accountRequestRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;



    public List<Account> getAccounts(Client client){
        List<Account> accounts = new ArrayList<>();
        for (long s : client.getAccountNumbers()){
            accounts.add(accountRepository.findAccountByAccountNumber(s));
        }
        return accounts;
    }

    public double getNetTotal(Client c){

        double total = 0;
        for (Account account : getAccounts(c)) {
            total += account.getBalance();
        }
        return total;
    }

    public AccountRequest accountRequest(Client c, String accountType) {
        AccountRequest request = new AccountRequest(accountType, c.getUsername());
        return request;
    }

    public void undoTransaction(){
        //TODO: Implement
    }

    public String summary(Client c){
        String summary = "Account  |  Balance";
        for (Account account : getAccounts(c)){
            summary += account.getAccountType()+ ": " +account.displayBalance();
        }
        return summary;
    }

    public JointAccountRequest jointAccountRequest(String username, Account account, String otherUser){

        // otherUser should be a user in the ATM to be able to make an account "joint"
        JointAccountRequest request = new JointAccountRequest(username,
                account.getAccountType(),
                account.getAccountNumber(),
                otherUser);
        return request;
    }


}

