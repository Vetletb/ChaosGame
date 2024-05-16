package edu.ntnu.idatt2003.exceptions;

/**
 * Exception class for invalid types.
 */
public class InvalidTypeException extends Exception {
  /**
   * Constructs a new InvalidTypeException with the default message.
   */
  public InvalidTypeException() {
    super("Invalid type exception occurred.");
  }

  /**
   * Constructs a new InvalidTypeException with the specified message and cause.
   *
   * @param message the message
   * @param cause   the cause
   */
  public InvalidTypeException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new InvalidTypeException with the specified message.
   *
   * @param message the message
   */
  public InvalidTypeException(String message) {
    super(message);
  }
}
