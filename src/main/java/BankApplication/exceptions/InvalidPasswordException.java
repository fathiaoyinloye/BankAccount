package BankApplication.exceptions;

public class InvalidPasswordException extends BankException {
    public InvalidPasswordException() {
        super("Inputted Password is Invalid");
    }
}
