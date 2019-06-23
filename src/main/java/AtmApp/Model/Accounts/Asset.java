package AtmApp.Model.Accounts;

public abstract class Asset {
    private double buyingPrice;
    private double currentValue;
    private final String name;

    Asset(String name, double value){
        this.name = name;
        buyingPrice = value;
        currentValue = value;
    }

    public double getBuyingPrice(){return buyingPrice;}

    public void setBuyingPrice(){this.buyingPrice=currentValue;}


    public double getCurrentValue(){
        currentValue = updateValue();
        return currentValue;}

    public abstract double updateValue();

    public String getName(){ return name;}

    public double getProfit() { return getCurrentValue() - buyingPrice;}


    public abstract void addAsset(Asset asset);
    public abstract void deductAsset(double ratio);
}
