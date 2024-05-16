package edu.ntnu.idatt2003.exceptions;

/**
 * Exception class for exceptions caused by observing.
 */
public class ObservingException extends Exception {
  /**
   * Constructs a new ObservingException with the default message.
   */
  public ObservingException() {
    super("Observing exception occurred.");
  }

  /**
   * Constructs a new ObservingException with the specified message and cause.
   *
   * @param message the message
   * @param cause the cause
   */
  public ObservingException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new ObservingException with the specified message.
   *
   * @param message the message
   */
  public ObservingException(String message) {
    super(message);
  }
}
