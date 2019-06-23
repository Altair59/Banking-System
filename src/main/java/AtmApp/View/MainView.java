package AtmApp.View;

import AtmApp.Model.Accounts.Account;
import AtmApp.Model.Accounts.Asset;
import AtmApp.Model.Accounts.AssetTypeAccount;
import AtmApp.Model.Accounts.LineOfCreditAccount;
import AtmApp.Model.Atm.Atm;
import AtmApp.Model.Atm.Cash;
import AtmApp.Model.Requests.AccountRequest;
import AtmApp.Model.Requests.UserRequest;
import AtmApp.Model.Users.Client;
import AtmApp.Model.Users.Employee;
import AtmApp.Model.Users.User;
import AtmApp.Service.*;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

//import com.vaadin.flow.server.PWA;

@Route

//@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {


    @Autowired
    UserRequestService userRequestService;

    @Autowired
    ClientSession clientSession;
    @Autowired
    AtmService atmService;

    @Autowired
    AssetTypeAccountService assetTypeAccountService;

    @Autowired
    CashService cashService;

    @Autowired
    LineOfCreditService lineOfCreditService;

    LoginView loginView;

    UserRequestForm userRequestForm;

    UserRequestForm signUpForm;


    UserRequestGrid userRequestGrid;

    ClientHome clientHome;

    TransactionButtons transactionButtons;

    TransferDialog transferDialog;

    BankManagerHome bankManagerHome;


    CheckBalanceDialog checkBalanceDialog;

    AtmDialog withdrawDialog;

    AtmDialog depositDialog;

    AccountRequestGrid accountRequestGrid;

    AtmBalanceView atmBalanceView;

    AtmDialog atmReloadForm;

    AccountRequestDialog accountRequestDialog;

    @Autowired
    GeneralService generalService;
    @Autowired
    ClientService clientService;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRequestService accountRequestService;
    @Autowired
    BankManagerService bankManagerService;

    public MainView() {
        accountRequestDialog = new AccountRequestDialog();
        atmReloadForm = new AtmDialog();
        atmBalanceView = new AtmBalanceView();
        signUpForm = new UserRequestForm();
        accountRequestGrid = new AccountRequestGrid();
        userRequestGrid = new UserRequestGrid();
        bankManagerHome = new BankManagerHome();
        depositDialog = new AtmDialog();
        loginView = new LoginView();
        clientHome = new ClientHome();
        transferDialog = new TransferDialog();
        transactionButtons = new TransactionButtons();
        checkBalanceDialog = new CheckBalanceDialog();
        withdrawDialog = new AtmDialog();
        userRequestForm = new UserRequestForm();

        setLoginView();
        add(loginView);

    }
    public void setLoginView(){

        loginView.signup.addClickListener(event -> {
            removeAll();
            signUpForm.signUp.addClickListener(event1 -> {
                try {
                    generalService.createUserRequest(signUpForm.userName.getValue(), signUpForm.password.getValue(),
                            signUpForm.email.getValue(), signUpForm.userType.getValue());
                } catch (Exception e) {

                }
            });
            signUpForm.back.addClickListener(event1 -> {
                removeAll();
                add(loginView);
            });
            signUpForm.addSignUpElements();

            add(signUpForm);


        });



        loginView.signin.addClickListener(event -> {
            clientSession.setAuthenticated(generalService.login(loginView.username.getValue(), loginView.password.getValue()));
            if (clientSession.isAuthenticated()){
                User user = generalService.getUser(loginView.username.getValue());
                if (user instanceof Client) {
                    clientSession.setClient((Client) generalService.getUser(loginView.username.getValue()));
                    removeAll();
                    setClientHome();
                } else if (user instanceof Employee) {
                    removeAll();
                    setBankManagerHome();
                }
            }
            else{
                loginView.add(new Text("Error"));
            }
        });
    }


    public void setBankManagerHome() {
        add(bankManagerHome);
        bankManagerHome.createUser.addClickListener(event -> {
            setUserRequestGrid();
        });
        bankManagerHome.createAccount.addClickListener(event -> {
            setAccountRequestGrid();
        });
        bankManagerHome.reloadAtm.addClickListener(event -> {
            setAtmRequestView();
        });
        bankManagerHome.undoTransactions.addClickListener(event -> {

        });
        bankManagerHome.signout.addClickListener(event -> {
            removeAll();
            add(loginView);
        });

    }


    public void setAtmRequestView() {
        atmBalanceView.atmGrid.addColumn(new NativeButtonRenderer<>("Reload", clickedItem -> {
            atmReloadForm.performAction.setText("Reload");

            atmReloadForm.performAction.addClickListener(event -> {

                try {
                    Cash cash = clickedItem;
                    int[] bills = new int[4];
                    bills[0] = atmReloadForm.fiveField.getValue().intValue();
                    bills[1] = atmReloadForm.tenField.getValue().intValue();
                    bills[2] = atmReloadForm.twentyField.getValue().intValue();
                    bills[3] = atmReloadForm.fiftyField.getValue().intValue();

                    bankManagerService.reload(cash.getCashId(), bills);
                    atmBalanceView.atmGrid.select(clickedItem);
                    atmBalanceView.atmGrid.asSingleSelect().setValue(cashService.getCash(cash.getCashId()));
                    atmReloadForm.balance.setText(Double.toString(atmBalanceView.atmGrid.asSingleSelect().getValue().getBalance()));
                    atmReloadForm.status.setText("Success");
                } catch (Exception e) {
                    atmReloadForm.status.setText("Failed");
                }
            });
            atmReloadForm.open();
        }));
        atmBalanceView.atmGrid.setItems(bankManagerService.findAllCash());
        bankManagerHome.setContent(atmBalanceView.atmGrid);
    }




    public void setAccountRequestGrid() {
        List<AccountRequest> accountRequestList = new ArrayList<>();
        accountRequestGrid.accountRequestGrid.setItems(accountRequestService.showRequests());
        accountRequestGrid.accountRequestGrid.addColumn(new NativeButtonRenderer<>("Create", clickedItem -> {
            try {
                bankManagerService.createAccount(clickedItem);
                accountRequestList.remove(clickedItem);
                accountRequestGrid.accountRequestGrid.setItems(clickedItem);
            } catch (Exception e) {

            }

        }));
        bankManagerHome.setContent(accountRequestGrid.accountRequestGrid);


    }

    public void setUserRequestGrid() {
        userRequestForm.addUserRequestElements();
        List<UserRequest> userRequests = userRequestService.getUserRequests();
        userRequestGrid.userRequestGrid.setItems(userRequests);
        userRequestGrid.userRequestGrid.addSelectionListener(event -> {
            UserRequest userRequest = userRequestGrid.userRequestGrid.asSingleSelect().getValue();
            userRequestForm.userName.setValue(userRequest.getUsername());
            userRequestForm.email.setValue(userRequest.getEmail());
            userRequestForm.userType.setValue(userRequest.getUserType());
            userRequestForm.create.addClickListener(event1 -> {
                try {
                    bankManagerService.createUser(userRequestForm.userName.getValue(), userRequest.getPassword(),
                            userRequestForm.email.getValue(), userRequestForm.userType.getValue());

                    userRequests.remove(userRequest);
                    userRequestGrid.userRequestGrid.setItems(userRequests);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

        });
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.addToPrimary(userRequestGrid.userRequestGrid);
        splitLayout.addToSecondary(userRequestForm);
        bankManagerHome.setContent(splitLayout);


    }


    public void setClientHome(){
        add(clientHome);
        clientHome.setVisible(true);
        List<Account> accounts = clientService.getAccounts(clientSession.getClient());
        clientHome.setComboBoxItems(accounts);
        clientHome.signOut.addClickListener(event -> {
            removeAll();
            add(loginView);
        });

        ArrayList<String> accountPrototypes = new ArrayList<String>();
        accountPrototypes.add("Chequing Account");
        accountPrototypes.add("Savings Account");
        accountPrototypes.add("Credit Card Account");
        accountPrototypes.add("Line of Credit Account");
        accountPrototypes.add("Unregistered Investment Account");
        accountPrototypes.add("Registered Investment Account");
        accountPrototypes.add("Travel Credit Card Account");

        accountRequestDialog.accountComboBox.setItems(accountPrototypes);
        clientHome.accountRequestButton.addClickListener(event -> {
            accountRequestDialog.send.addClickListener(event1 -> {
                if (accountRequestDialog.accountComboBox.isEmpty()) {
                    accountRequestDialog.status.setText("Pick an account");
                } else {
                    String accountType = accountRequestDialog.accountComboBox.getValue();
                    accountRequestService.saveAccountRequest(accountType, clientSession.getClient().getUsername());
                    accountRequestDialog.status.setText("Success");
                }
            });
            accountRequestDialog.open();
        });

        getTransactionButtons();
    }


    public void getTransactionButtons(){

        clientHome.accountComboBox.addValueChangeListener(event -> {
            clientSession.setAccount(clientHome.accountComboBox.getValue());
            transactionButtons.setButtons(clientSession.getAccount());
            setTransactionButtonListeners();
            clientHome.setContent(transactionButtons);
        });

    }

    public void setTransactionButtonListeners(){

        transactionButtons.transferButton.addClickListener(event -> {
            transferDialog.label.setText(Double.toString(clientSession.getAccount().getBalance()));
            transferDialog.clearTransferDialog();
            transferDialog.transferButton.addClickListener(event1 -> {
                try {
                    String toAccountNumber  =  Integer.toString(transferDialog.accountNumberField.getValue().intValue());
                    Account fromAccount = clientSession.getAccount();
                    if(fromAccount instanceof AssetTypeAccount) {
                        assetTypeAccountService.transfer(fromAccount.getAccountNumber(), transferDialog.amountField.getValue(), Long.valueOf(toAccountNumber));
                    } else if (fromAccount instanceof LineOfCreditAccount) {

                    }

                    Account updatedAccount = accountService.updateAccount(clientSession.getAccount());
                    clientSession.setAccount(updatedAccount);
                    transferDialog.label.setText(Double.toString(clientSession.getAccount().getBalance()));
                    transferDialog.status.setText("Success");
                    transferDialog.clearTransferDialog();
                }catch(Exception e){
                    if (e.getMessage() != null) {

                        transferDialog.status.setText(e.getMessage());
                    } else {
                        transferDialog.status.setText("Error");
                    }

                }
                });
            transferDialog.open();});


        transactionButtons.withdrawButton.addClickListener(event -> {

            int[] balance = clientSession.getAtm().getCashManager().getStock();
            withdrawDialog.fiftyField.setMinLength(0);
            withdrawDialog.fiftyField.setMaxLength(balance[0]);
            withdrawDialog.twentyField.setMinLength(0);
            withdrawDialog.twentyField.setMaxLength(balance[1]);
            withdrawDialog.tenField.setMinLength(0);
            withdrawDialog.tenField.setMaxLength(balance[2]);
            withdrawDialog.fiveField.setMinLength(0);
            withdrawDialog.fiveField.setMaxLength(balance[3]);
            withdrawDialog.performAction.setText("Withdraw");
            withdrawDialog.performAction.addClickListener(event1 -> {
                try {
                    /*accountService.withdraw(clientSession.getAccount().getAccountNumber(), clientSession.getAtm().getAtmId(), 50, withdrawDialog.fiftyField.getValue().intValue());
                    accountService.withdraw(clientSession.getAccount().getAccountNumber(), clientSession.getAtm().getAtmId(), 20, withdrawDialog.twentyField.getValue().intValue());
                    accountService.withdraw(clientSession.getAccount().getAccountNumber(), clientSession.getAtm().getAtmId(), 10, withdrawDialog.tenField.getValue().intValue());
                    accountService.withdraw(clientSession.getAccount().getAccountNumber(), clientSession.getAtm().getAtmId(), 5, withdrawDialog.fiveField.getValue().intValue());
                    */
                    Cash cash = clientSession.getAtm().getCashManager();
                    int[] bills = new int[4];
                    bills[0] = withdrawDialog.fiveField.getValue().intValue();
                    bills[1] = withdrawDialog.tenField.getValue().intValue();
                    bills[2] = withdrawDialog.twentyField.getValue().intValue();
                    bills[3] = withdrawDialog.fiftyField.getValue().intValue();

                    cashService.withdraw(bills, cash.getCashId());
                    withdrawDialog.status.setText("Success");
                    Atm atm = atmService.getAtm(clientSession.getAtm().getAtmId());
                    Account account = accountService.updateAccount(clientSession.getAccount());
                    clientSession.setAccount(account);
                    clientSession.setAtm(atm);


                } catch (Exception e) {
                    if (!e.getMessage().isEmpty()) {
                        withdrawDialog.status.setText(e.getMessage());
                    } else {
                        withdrawDialog.status.setText("Error");
                    }
                }
            });
            withdrawDialog.open();


        });
        transactionButtons.depositButton.addClickListener(event -> {
            depositDialog.fiftyField.setMinLength(0);
            depositDialog.fiftyField.setMaxLength(20);
            depositDialog.twentyField.setMinLength(0);
            depositDialog.twentyField.setMaxLength(20);
            depositDialog.tenField.setMinLength(0);
            depositDialog.tenField.setMaxLength(20);
            depositDialog.fiveField.setMinLength(0);
            depositDialog.fiveField.setMaxLength(20);
            depositDialog.performAction.setText("Deposit");

            depositDialog.performAction.addClickListener(event1 -> {
                try {
                    /*accountService.deposit(clientSession.getAccount().getAccountNumber(), clientSession.getAtm().getAtmId(), 50, depositDialog.fiftyField.getValue().intValue());
                    accountService.deposit(clientSession.getAccount().getAccountNumber(), clientSession.getAtm().getAtmId(), 20, depositDialog.twentyField.getValue().intValue());
                    accountService.deposit(clientSession.getAccount().getAccountNumber(), clientSession.getAtm().getAtmId(), 10, depositDialog.tenField.getValue().intValue());
                    accountService.deposit(clientSession.getAccount().getAccountNumber(), clientSession.getAtm().getAtmId(), 5, depositDialog.fiveField.getValue().intValue());

                    */

                    Cash cash = clientSession.getAtm().getCashManager();
                    double amount = 0;
                    amount += 5*depositDialog.fiveField.getValue().intValue();
                    amount = 10*depositDialog.tenField.getValue().intValue();
                    amount = 20*depositDialog.twentyField.getValue().intValue();
                    amount = 50*depositDialog.fiftyField.getValue().intValue();

                    cashService.deposit(amount, cash.getCashId());
                    depositDialog.status.setText("Success");
                    Atm atm = atmService.getAtm(clientSession.getAtm().getAtmId());
                    Account account = accountService.updateAccount(clientSession.getAccount());
                    clientSession.setAccount(account);
                    clientSession.setAtm(atm);


                } catch (Exception e) {
                    if (!e.getMessage().isEmpty()) {
                        depositDialog.status.setText(e.getMessage());
                    } else {
                        depositDialog.status.setText("Error");
                    }
                }
            });
            depositDialog.open();

        });


        transactionButtons.checkBalance.addClickListener(event -> {
            Account account = clientSession.getAccount();
            clientSession.setAccount(accountService.updateAccount(account));
            checkBalanceDialog.transactionGrid.setItems(clientSession.getAccount().getAllTransactions());
            checkBalanceDialog.transactionGrid.addColumn(new NativeButtonRenderer<>("Reload", clickedItem -> {
                if (clickedItem.getType().equals("transfer")) {
                    accountService.sendUndoRequest(clickedItem);
                }

            }));
            checkBalanceDialog.balance.setValue(Double.toString(clientSession.getAccount().getBalance()));
            checkBalanceDialog.open();


        });
    }





}
