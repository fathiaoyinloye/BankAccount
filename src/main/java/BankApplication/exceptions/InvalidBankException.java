package BankApplication.exceptions;

public class InvalidBankException extends RuntimeException {
    public InvalidBankException() {
        super("Bank not part of the registerd bank");
    }
}
