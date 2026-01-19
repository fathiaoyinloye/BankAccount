package BankApplication.exceptions;

public class InsufficientFundException extends BankException {
    public InsufficientFundException() {
        super("Insufficient Fund");
    }
}
