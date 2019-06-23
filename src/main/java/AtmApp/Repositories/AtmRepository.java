package AtmApp.Repositories;

import AtmApp.Model.Atm.Atm;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AtmRepository extends MongoRepository<Atm, String> {

    Atm findByAtmId(String id);

}
