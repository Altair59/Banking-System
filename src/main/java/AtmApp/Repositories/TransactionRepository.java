package AtmApp.Repositories;

import AtmApp.Model.Transaction.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    //Transaction findByFromAccountIdEqualsOrToAccountIdEquals(String userId);
}

