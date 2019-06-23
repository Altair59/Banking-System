package AtmApp.Repositories;

import AtmApp.Model.Requests.UserRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRequestRepository extends MongoRepository<UserRequest, String> {

    UserRequest findByUsername(String username);

}
