package AtmApp.Service;

import AtmApp.Model.Requests.UserRequest;
import AtmApp.Repositories.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRequestService {

    @Autowired
    UserRequestRepository userRequestRepository;

    public List<UserRequest> getUserRequests() {
        return userRequestRepository.findAll();
    }


}
