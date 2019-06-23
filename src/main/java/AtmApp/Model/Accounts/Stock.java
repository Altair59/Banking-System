package AtmApp.Model.Accounts;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Random;

public class Stock extends Asset{
    private double shares;
    private double currentStockPrice;

    public Stock(String name, double price, double shares ){
        super(name, price*shares);
        this.shares = shares;
        this.currentStockPrice = price;
    }

    public void addAsset( Asset asset){
        this.shares += ((Stock) asset).getShares();
    }

    public void deductAsset( double ratio) {
        this.shares -= shares*(1-ratio);
    }

    public double getShares() { return shares;}

    public double updateValue(){
        return shares*getCurrentStockPrice();
    }


    public double getCurrentStockPrice(){
        currentStockPrice = currentStockPrice + (currentStockPrice*randomNumber());
        return currentStockPrice;
    }
    private double randomNumber(){
        double r = (new Random()).nextDouble();
        if (r%2==0) {
            return r;
        }
        else {
            return -r;
        }
    }
}
