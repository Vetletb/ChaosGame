package edu.ntnu.idatt2003.exceptions;

/**
 * Exception class for ChaosGameFileHandler.
 */
public class ChaosGameFileHandlerException extends Exception {
  /**
   * Constructs a new ChaosGameFileHandlerException with the default message.
   */
  public ChaosGameFileHandlerException() {
    super("An error occurred while handling the file");
  }

  /**
   * Constructs a new ChaosGameFileHandlerException with the specified message and cause.
   *
   * @param message the message
   * @param cause the cause
   */
  public ChaosGameFileHandlerException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new ChaosGameFileHandlerException with the specified message.
   *
   * @param message the message
   */
  public ChaosGameFileHandlerException(String message) {
    super(message);
  }
}
