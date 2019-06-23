package AtmApp.Service;

import AtmApp.Model.Accounts.*;
import AtmApp.Model.Atm.Atm;
import AtmApp.Model.Atm.Cash;
import AtmApp.Model.Transaction.Transaction;
import AtmApp.Model.Transaction.UndoTransaction;
import AtmApp.Model.Users.*;
import AtmApp.Repositories.*;
import AtmApp.Model.Requests.AccountRequest;
import AtmApp.Model.Requests.UserRequest;
import com.sun.xml.internal.bind.AccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Line;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankManagerService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRequestRepository userRequestRepository;

    @Autowired
    private AccountRequestRepository accountRequestRepository;
    @Autowired
    private AtmRepository atmRepository;

    @Autowired
    private AssetTypeAccountService assetTypeAccountService;

    @Autowired
    private LineOfCreditService lineOfCreditService;

    @Autowired
    private CashRepository cashRepository;

    private AccountFactory accountFactory = new AccountFactory();


    //adding user from User Request.
    public void addFromUserRequest(String username) throws Exception {
        UserRequest userRequest = userRequestRepository.findByUsername(username);
        if (userRequest != null) {
            createUser(userRequest.getUsername(), userRequest.getPassword(),
                    userRequest.getEmail(), userRequest.getUserType());
        } else {
            System.out.println("Failed. No user selected. Please Re-enter username.");

        }

    }

    public void createAtm() {
        Atm atm = new Atm();
        atmRepository.save(atm);
    }

    public void reload(String cashId, int[] bills) {
        Cash cash = cashRepository.findCashByCashId(cashId);
        cash.reStock(bills);
    }

    //creating a user
    public void createUser(String username, String password, String email, String userType) throws Exception {
        User p = null;
        if (userType.equals("Client")) {
            p = new Client(username, email);
        } else if (userType.equals("Bank Manager")) {
            p = new BankManager(username, email);
        } else if (userType.equals("Bank Teller")) {
            p = new BankTeller(username, email);
        } else if (userType.equals("Auditor")) {
            //Need clients
            //p = new Auditor(username, email);
        }

        p.changePassword("", password);
        if (userRepository.findByUsername(username) != null) {
            throw new Exception("The user already exists");
        }
        else if(p != null){
            userRepository.save(p);
        }
    }

    public void createAccount(AccountRequest accountRequest) {
        accountFactory.createAccount(accountRequest);
    }

    public List<UserRequest> getUserRequests() {
//        Map<Integer, UserRequest> map = new HashMap<Integer, UserRequest>();
//        int i = 1;
//        for (UserRequest userRequest : userRequestRepository.findAll()) {
//            map.put(1, userRequest);
//            i += 1;
//        }
//        return map;
        return userRequestRepository.findAll();
    }


    public Map<Integer, AccountRequest> getAccountRequests() throws Exception {
        Map<Integer, AccountRequest> map = new HashMap<Integer, AccountRequest>();
        int i = 1;
        for (AccountRequest accountRequest : accountRequestRepository.findAll()) {
            map.put(1, accountRequest);
            i += 1;
        }
        return map;
    }

    public void approveUndo(UndoTransaction undoTransaction) throws Exception {
        Transaction transaction = undoTransaction.getTransaction();
        long fromAccountId = transaction.getFromAccountNumber();
        long toAccountId = transaction.getToAccountNumber();
        String transactionType = transaction.getType();
        double amount = transaction.getAmount();

        Account toAccount = accountRepository.findAccountByAccountNumber(toAccountId);
        try {
            if (toAccount instanceof AssetTypeAccount) {
                assetTypeAccountService.transfer(toAccountId, amount, fromAccountId);
            } else if (toAccount instanceof LineOfCreditAccount) {
                lineOfCreditService.transfer(toAccountId, amount, fromAccountId);
            }

        } catch (Exception e) {
            throw new Exception("Cannot Undo");
        }
    }
<<<<<<< HEAD
=======

    public List<Atm> findAllAtm() {
        return atmRepository.findAll();
    }
    public List<Cash> findAllCash() {
        return cashRepository.findAll();
    }


>>>>>>> a703e50b10f19b3b1a21103b1933f04a05add398
}

