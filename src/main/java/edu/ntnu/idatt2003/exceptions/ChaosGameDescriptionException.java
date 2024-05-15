package edu.ntnu.idatt2003.exceptions;

/**
 * Exception class for ChaosGameDescription.
 */
public class ChaosGameDescriptionException extends Exception {
  /**
   * Constructs a new ChaosGameDescriptionException with the default message.
   */
  public ChaosGameDescriptionException() {
    super("An error occurred in the ChaosGameDescription class.");
  }

  /**
   * Constructs a new ChaosGameDescriptionException with the specified message.
   *
   * @param message the message
   */
  public ChaosGameDescriptionException(String message) {
    super(message);
  }

  /**
   * Constructs a new ChaosGameDescriptionException with the specified message and cause.
   *
   * @param message the message
   * @param cause the cause
   */
  public ChaosGameDescriptionException(String message, Throwable cause) {
    super(message, cause);
  }
}
