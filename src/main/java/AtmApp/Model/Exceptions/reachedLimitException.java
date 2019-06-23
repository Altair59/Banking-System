package AtmApp.Model.Exceptions;

public class reachedLimitException extends Exception {
    public String toString(){
        return ("Sorry, you've reached the limit");
    }
}