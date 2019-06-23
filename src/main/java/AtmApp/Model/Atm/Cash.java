package AtmApp.Model.Atm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.IOException;

@Document(collection = "Cash")
public class Cash {

    @Id
    private String cashId;
    private int fives = 0;
    private int tens = 0;
    private int twenties = 0;
    private int fifties = 0;
    private double depositedCash;

    public Cash(){
        //default stock
        fives = 20;
        tens = 20;
        twenties = 20;
        fifties = 20;
    }

    public String getCashId() {
        return cashId;
    }

    public boolean checkFunds(int amount) {
        int totalCash = 0;
        return (totalCash > amount);
    }

    public int[] getStock() {
        int[] bills = new int[4];
        bills[0] = fives;
        bills[1] = tens;
        bills[2] = twenties;
        bills[3] = fifties;

        return bills;
    }

    public void reStock(int[] bills) {
        //method for BM to refill cash reserves in ATM
        this.fives += bills[0];
        this.tens += bills[1];
        this.twenties += bills[2];
        this.fifties += bills[3];
    }

    public boolean enough_bills(int num_fives, int num_tens, int num_twenties, int num_fifties){
        return fives >= num_fives && tens >= num_tens && twenties >= num_twenties && fifties >= num_fifties;
    }

    public double getBalance() {
        return fives*5+tens*10+twenties*20+fifties*50;
    }

    public double getDepositedCash() {
        return depositedCash;
    }

    public void setDepositedCash(double depositedCash) {
        this.depositedCash = depositedCash;
    }

    public void removeFives(int fives) {
        this.fives -= fives;
    }

    public void removeTens(int tens) {
        this.tens -= tens;
    }

    public void removeTwenties(int twenties) {
        this.twenties -= twenties;
    }

    public void removeFifties(int fifties) {
        this.fifties -= fifties;
    }

    public int getFives() {
        return fives;
    }

    public int getTens() {
        return tens;
    }

    public int getTwenties() {
        return twenties;
    }

    public int getFifties() {
        return fifties;
    }
}
