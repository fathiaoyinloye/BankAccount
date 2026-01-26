package BankApplication.exceptions;

public class InvalidBvnException extends BankException {
    public InvalidBvnException() {
        super("BVN Does not Exist");
    }
}
