package pl.bank.exception;

public class PinNumberException extends RuntimeException {
    public PinNumberException() {

    }

    public PinNumberException(String message) {
        super(message);
    }

    public PinNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public PinNumberException(Throwable cause) {
        super(cause);
    }

    public PinNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
