package edu.ntnu.idatt2003.exceptions;

/**
 * Exception class for wrong length.
 */
public class WrongLengthException extends Exception {
  /**
   * Constructs a new WrongLengthException with the default message.
   */
  public WrongLengthException() {
    super("Wrong length exception occurred.");
  }

  /**
   * Constructs a new WrongLengthException with the specified message and cause.
   *
   * @param message the message
   * @param cause the cause
   */
  public WrongLengthException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new WrongLengthException with the specified message.
   *
   * @param message the message
   */
  public WrongLengthException(String message) {
    super(message);
  }
}
