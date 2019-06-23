package AtmApp.Model.Exceptions;

public class maximumWithdrawException extends Exception {
    public String toString(){
        return ("Illegal withdraw: maximum withdraw reached");
    }
}

