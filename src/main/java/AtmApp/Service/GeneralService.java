package AtmApp.Service;


import AtmApp.Model.Users.User;
import AtmApp.Repositories.UserRepository;
import AtmApp.Repositories.UserRequestRepository;
import AtmApp.Model.Requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneralService {



    @Autowired
    private UserRequestRepository userRequestRepository;
    @Autowired
    private UserRepository userRepository;




    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }


    public void createUserRequest(String username, String password, String email, String userType) throws Exception {
        UserRequest userRequest = new UserRequest(username, password, email, userType);
        userRequestRepository.save(userRequest);
    }

    public boolean login(String username, String password) {
        User u = userRepository.findByUsername(username);
        if (u != null) {
            String s = u.getPassword();
            return s.equals(password);
        } else {
            return false;
        }
    }



}

