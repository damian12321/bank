package pl.springbank.bank.exception;

public class NoAccessException extends RuntimeException {

    public NoAccessException(String message) {
        super(message);
    }

}
