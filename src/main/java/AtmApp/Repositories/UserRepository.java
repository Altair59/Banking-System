package AtmApp.Repositories;

import AtmApp.Model.Users.Client;
import AtmApp.Model.Users.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    Client findByUsername(String username);

}
