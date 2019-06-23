package AtmApp.Service;

import AtmApp.Model.Accounts.Account;
import AtmApp.Model.Requests.AccountRequest;
import AtmApp.Repositories.AccountRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRequestService {

    @Autowired
    AccountRequestRepository accountRequestRepository;


    public List<AccountRequest> showRequests() {
        return accountRequestRepository.findAll();
    }

    public void saveAccountRequest(String accountType, String username) {
        AccountRequest accountRequest = new AccountRequest(accountType, username);
        accountRequestRepository.save(accountRequest);
    }


}
