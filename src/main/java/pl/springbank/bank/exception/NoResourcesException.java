package pl.springbank.bank.exception;

public class NoResourcesException extends RuntimeException {
    public NoResourcesException() {

    }

    public NoResourcesException(String message) {
        super(message);
    }

    public NoResourcesException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoResourcesException(Throwable cause) {
        super(cause);
    }

    public NoResourcesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
