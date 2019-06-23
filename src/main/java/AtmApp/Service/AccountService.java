package AtmApp.Service;


import AtmApp.Model.Accounts.*;
import AtmApp.Model.Atm.Atm;
import AtmApp.Model.Transaction.Transaction;
import AtmApp.Model.Transaction.UndoTransaction;
import AtmApp.Model.Transaction.TransactionPlus;
import AtmApp.Repositories.AccountRepository;
import AtmApp.Repositories.AtmRepository;
import AtmApp.Repositories.UndoTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;

@Service
public abstract class AccountService {

    @Autowired
    UndoTransactionRepository undoTransactionRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AtmRepository atmRepository;


    public void sendUndoRequest(Transaction transaction) {
        UndoTransaction undoTransaction = new UndoTransaction(transaction);
        undoTransactionRepository.save(undoTransaction);
    }

    public String generateAccountNumber() {
        String s = "";
        for (int i = 0; i < 6; i++) {
            s = s.concat(String.valueOf((int) (Math.random() * 9)));
        }
        return s;
    }


    /*public List<Account> getAllAccountPrototypes() {
        Chequing chequing = new Chequing(generateAccountNumber());
        Savings savings = new Savings(generateAccountNumber());
        CreditCard creditCard = new CreditCard(generateAccountNumber());
        LineOfCredit lineOfCredit = new LineOfCredit(generateAccountNumber());
        List<Account> accounts = new ArrayList<>();
        accounts.add(chequing);
        accounts.add(savings);
        accounts.add(creditCard);
        accounts.add(savings);
        return accounts;
    }*/


    public Account getAccount(long accountNumber) throws Exception{
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        if (account == null) {
            throw new Exception("Account number is incorrect.");
        } else {
            return account;
        }
    }

    @Transactional
    public void deposit(double amount, long accountNumber) throws Exception{
        Account toAccount = getAccount(accountNumber);
        changeBalance(accountNumber, amount);
        toAccount.recordTransaction(new Transaction(accountNumber, accountNumber, "deposit", amount));
        accountRepository.save(toAccount);
    }

    @Transactional
    public void deposit(double amount, long accountNumber, String conversion ) throws Exception{
        Account toAccount = getAccount(accountNumber);
        changeBalance(accountNumber, amount);
        toAccount.recordTransaction(new TransactionPlus(accountNumber, accountNumber,
                "deposit", amount, conversion));
        accountRepository.save(toAccount);
    }

    public void changeBalance(long accountNumber, double amount) throws Exception {
        Account toAccount = getAccount(accountNumber);
        toAccount.setBalance(toAccount.getBalance()+amount);
        accountRepository.save(toAccount);
    }

    public void update(Observable o, Object arg) {
        update((LocalDate) arg);
    }

    protected abstract void update(LocalDate date);

    public Account updateAccount(Account account) {
        return accountRepository.findAccountByAccountNumber(account.getAccountNumber());
    }

    /*
    @Transactional
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) throws Exception {
        Account fromAccount = getAccount(fromAccountNumber);
        Account toAccount = getAccount(toAccountNumber);
        Transaction transaction = new Transaction(fromAccount.getAccountNumber(), toAccountNumber, amount, "Transfer");
        fromAccount.send(amount);
        toAccount.receive(amount);
        fromAccount.addTransaction(transaction);
        toAccount.addTransaction(transaction);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);


    }

    @Transactional
    public void withdraw( String accountNumber, String atmId, int cash, int amount) throws Exception {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        Atm atm = atmRepository.findByAtmId(atmId);
        Transaction transaction = new Transaction(account.getAccountNumber(), atm.getAtmId(), cash * amount, "Atm_withdraw");
        atm.withdraw(cash, amount);
        account.send(cash * amount);
        atm.addTransaction(transaction);
        account.addTransaction(transaction);
        atmRepository.save(atm);
        accountRepository.save(account);
    }

    @Transactional
    public void deposit(String accountNumber, String atmId, int cash, int amount) throws Exception {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        Atm atm = atmRepository.findByAtmId(atmId);
        Transaction transaction = new Transaction(atm.getAtmId(), account.getAccountNumber(), cash * amount, "Atm_deposit");
        atm.deposit(cash, amount);
        account.receive(cash * amount);
        atm.addTransaction(transaction);
        account.addTransaction(transaction);
        atmRepository.save(atm);
        accountRepository.save(account);

    }
    */

}

