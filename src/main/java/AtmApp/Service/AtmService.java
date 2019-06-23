package AtmApp.Service;

import AtmApp.Model.Atm.Atm;
import AtmApp.Model.Atm.Cash;
import AtmApp.Model.Users.User;
import AtmApp.Repositories.AtmRepository;
import AtmApp.Repositories.CashRepository;
import AtmApp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtmService {

    @Autowired
    AtmRepository atmRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CashService cashService;

    public Atm getAtm(String atmId){
        return atmRepository.findByAtmId(atmId);
    }
    public void saveAtm(Atm atm){
        atmRepository.save(atm);
    }

    public Atm getFirstAtm(){
        return atmRepository.findAll().get(0);
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

    //user deposits cash into atm via keyboard input
    public void deposit(int amount, String atmId) {
        Atm atm = getAtm(atmId);
        Cash cash = atm.getCashManager();
        cashService.deposit(amount, cash.getCashId());
        atmRepository.save(atm);
    }

}

