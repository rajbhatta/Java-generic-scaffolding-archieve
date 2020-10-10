package raj.java.generic.exception;

public class OracleException extends Exception {
  public OracleException() {}

  public OracleException(String message) {
    super(message);
  }

  public OracleException(String message, Throwable cause) {
    super(message, cause);
  }

  public OracleException(Throwable cause) {
    super(cause);
  }

  public OracleException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
