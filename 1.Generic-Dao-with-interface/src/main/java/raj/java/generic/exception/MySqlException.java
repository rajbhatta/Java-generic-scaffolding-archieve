package raj.java.generic.exception;

public class MySqlException extends Exception {
    public MySqlException() {
    }

    public MySqlException(String message) {
        super(message);
    }

    public MySqlException(String message, Throwable cause) {
        super(message, cause);
    }

    public MySqlException(Throwable cause) {
        super(cause);
    }

    public MySqlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
