package edu.ntnu.idatt2003.exceptions;

/**
 * Exception class for invalid signs.
 */
public class InvalidSignException extends Exception {

  /**
   * Constructs a new InvalidSignException with the default message.
   */
  public InvalidSignException() {
    super("Invalid sign exception occurred.");
  }

  /**
   * Constructs a new InvalidSignException with the specified message and cause.
   *
   * @param message the message
   * @param cause the cause
   */
  public InvalidSignException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new InvalidSignException with the specified message.
   *
   * @param message the message
   */
  public InvalidSignException(String message) {
    super(message);
  }
}
