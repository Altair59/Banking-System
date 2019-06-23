package AtmApp;

import AtmApp.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {


    @Autowired
    ClientSession clientSession;
    @Autowired
    AtmService atmService;
    @Autowired
    GeneralService generalService;
    @Autowired
    ClientService clientService;
    @Autowired
    AccountService accountService;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        clientSession.setAtm(atmService.getFirstAtm());
    }
}
