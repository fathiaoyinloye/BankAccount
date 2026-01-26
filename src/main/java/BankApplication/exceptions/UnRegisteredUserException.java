package BankApplication.exceptions;

public class UnRegisteredUserException extends BankException {
    public UnRegisteredUserException() {
        super("User is yet to register, Please Register to create Account");
    }
}
