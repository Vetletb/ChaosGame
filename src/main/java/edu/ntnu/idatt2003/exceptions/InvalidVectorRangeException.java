package edu.ntnu.idatt2003.exceptions;

/**
 * Exception class for invalid vector ranges.
 */
public class InvalidVectorRangeException extends Exception {

  /**
   * Constructs a new InvalidVectorRangeException with the default message.
   */
  public InvalidVectorRangeException() {
    super("Invalid vector range exception occurred.");
  }

  /**
   * Constructs a new InvalidVectorRangeException with the specified message and cause.
   *
   * @param message the message
   * @param cause   the cause
   */
  public InvalidVectorRangeException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new InvalidVectorRangeException with the specified message.
   *
   * @param message the message
   */
  public InvalidVectorRangeException(String message) {
    super(message);
  }
}
