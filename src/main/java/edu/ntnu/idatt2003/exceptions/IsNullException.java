package edu.ntnu.idatt2003.exceptions;

/**
 * Exception class for objects that are null.
 */
public class IsNullException extends Exception {
  /**
   * Constructs a new NullException with the default message.
   */
  public IsNullException() {
    super("Null exception occurred.");
  }

  /**
   * Constructs a new NullException with the specified message and cause.
   *
   * @param message the message
   * @param cause the cause
   */
  public IsNullException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new NullException with the specified message.
   *
   * @param message the message
   */
  public IsNullException(String message) {
    super(message);
  }
}
