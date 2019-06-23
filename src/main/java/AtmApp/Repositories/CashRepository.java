package AtmApp.Repositories;

import AtmApp.Model.Atm.Cash;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CashRepository extends MongoRepository<Cash, String> {
    Cash findCashByCashId(String id);
}
