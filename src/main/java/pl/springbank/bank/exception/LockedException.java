package pl.springbank.bank.exception;

public class LockedException extends RuntimeException {
    public LockedException() {

    }

    public LockedException(String message) {
        super(message);
    }

    public LockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LockedException(Throwable cause) {
        super(cause);
    }

    public LockedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
