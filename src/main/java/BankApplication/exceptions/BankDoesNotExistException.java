package BankApplication.exceptions;

public class BankDoesNotExistException extends RuntimeException {
    public BankDoesNotExistException() {
        super("Bank Does Not Exist");
    }
}
