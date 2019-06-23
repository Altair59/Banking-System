package AtmApp.Service;

import AtmApp.Model.Accounts.Account;
import AtmApp.Model.Atm.Atm;
import AtmApp.Model.Users.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientSession {


    private boolean authenticated = false;
    private Client client;
    private Atm atm;
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Atm getAtm() {
        return atm;
    }

    public void setAtm(Atm atm) {
        this.atm = atm;
    }
}
