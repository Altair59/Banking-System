package AtmApp.Repositories;

import AtmApp.Model.Requests.AccountRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRequestRepository extends MongoRepository<AccountRequest, String> {

    AccountRequest findByUsername(String userName);
}
