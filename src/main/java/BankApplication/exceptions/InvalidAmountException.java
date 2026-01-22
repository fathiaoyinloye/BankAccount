package BankApplication.exceptions;

public class InvalidAmountException extends BankException {
    public InvalidAmountException(){
        super("Amount cannot be less than fifty naira");
    }

}
