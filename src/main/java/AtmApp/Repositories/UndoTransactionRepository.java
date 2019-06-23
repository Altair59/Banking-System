package AtmApp.Repositories;


import AtmApp.Model.Transaction.UndoTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UndoTransactionRepository extends MongoRepository<UndoTransaction, String> {
    List<UndoTransaction> findAllByCompletedFalse();
}
