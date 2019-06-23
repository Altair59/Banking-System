package AtmApp.Service;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class currencyTable {
    private HashMap<String,Double> table;

    public currencyTable(){
        this.table = new HashMap<String, Double>();
        table.put("CAD",1.0);
        table.put("USD",1.09);
    }

    public void addCurrency(String currency,double rate){
        table.put(currency,rate);
    }

    public boolean checkCurrency(String currency){
        return table.containsKey(currency);
    }

    public double getRate(String currency){
        return table.get(currency);
    }
}

