package AtmApp.Repositories;

import AtmApp.Model.Accounts.Account;
import AtmApp.Model.Accounts.AssetTypeAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountRepository extends MongoRepository<Account, String> {

    Account findAccountByAccountNumber(long accountNumber);

    AssetTypeAccount findAccountByAccountType(String accountType);
}
