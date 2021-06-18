package pl.springbank.bank.exception;

public class LockedException extends RuntimeException {

    public LockedException(String message) {
        super(message);
    }

}
