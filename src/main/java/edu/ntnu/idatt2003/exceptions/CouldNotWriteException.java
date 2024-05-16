package edu.ntnu.idatt2003.exceptions;

/**
 * Exception class for when a file could not be written to.
 */
public class CouldNotWriteException extends Exception {
  /**
   * Constructs a new CouldNotWriteException with the default message.
   */
  public CouldNotWriteException() {
    super("Could not write exception occurred.");
  }

  /**
   * Constructs a new CouldNotWriteException with the specified message and cause.
   *
   * @param message the message
   * @param cause the cause
   */
  public CouldNotWriteException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new CouldNotWriteException with the specified message.
   *
   * @param message the message
   */
  public CouldNotWriteException(String message) {
    super(message);
  }
}
