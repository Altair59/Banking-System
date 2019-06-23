package AtmApp.Repositories;

import AtmApp.Model.Atm.AtmAlert;
import AtmApp.Model.Atm.Cash;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AtmAlertRepository extends MongoRepository<AtmAlert, String> {
    AtmAlert findAtmAlertByAlertId(String id);
}
