package AtmApp.Service;

import AtmApp.Model.Accounts.Account;
import AtmApp.Model.Transaction.Transaction;
import AtmApp.Model.Users.Auditor;
import AtmApp.Model.Users.Client;
import AtmApp.Model.Users.User;
import AtmApp.Repositories.AccountRepository;
import AtmApp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuditorService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    public ArrayList<ArrayList<Transaction>> getUserTransactions(Auditor auditor, String username){

        ArrayList<ArrayList<Transaction>> transactions = new ArrayList<>();
        User user = userRepository.findByUsername(username);
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts){
            long accountNumber = account.getAccountNumber();
            if(((Client) user).getAccountNumbers().contains(accountNumber)) {
                transactions.add(account.getAllTransactions());
            }
        }
        return transactions;
    }


}
