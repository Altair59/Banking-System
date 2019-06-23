package AtmApp.Model.Atm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Alert")
public class AtmAlert {

    @Id
    private String alertId;

    //Each of the boolean variables represent
    //whether a type of bill is understocked
    //(there are less than 20 of a bill).
    //True means there are less than 20.
    //False means there are 20 or more.
    private boolean bill50;
    private boolean bill20;
    private boolean bill10;
    private boolean bill5;
    private String date;

    public AtmAlert() {
        this.bill5 = false;
        this.bill10 = false;
        this.bill20 = false;
        this.bill50 = false;
    }


    public boolean isBill50() {
        return bill50;
    }

    public void setBill50(boolean bill50) {
        this.bill50 = bill50;
    }

    public boolean isBill20() {
        return bill20;
    }

    public void setBill20(boolean bill20) {
        this.bill20 = bill20;
    }

    public boolean isBill10() {
        return bill10;
    }

    public void setBill10(boolean bill10) {
        this.bill10 = bill10;
    }

    public boolean isBill5() {
        return bill5;
    }

    public void setBill5(boolean bill5) {
        this.bill5 = bill5;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        String output = "";
        if (bill5) {
            output+="Warning number of fives bills is less than 20.\n";
        }
        if (bill10) {
            String fileContent = "Warning number of tens bills is less than 20.\n";
        }
        if (bill20) {
            String fileContent = "Warning number of twenties bills is less than 20.\n";
        }
        if (bill50) {
            String fileContent = "Warning number of fifties bills is less than 20.\n";
        }
        return output;
    }
}
