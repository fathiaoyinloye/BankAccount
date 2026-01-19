package BankApplication.exceptions;

public class NoTransactionMadeException extends BankException {
    public NoTransactionMadeException() {
        super("No Transaction have been made yet ");
    }
}
