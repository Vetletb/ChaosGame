package edu.ntnu.idatt2003.exceptions;

/**
 * Exception class for ChaosGameDescriptionFactory.
 */
public class ChaosGameDescriptionFactoryException extends Exception {
  /**
   * Constructs a new ChaosGameDescriptionFactoryException with the default message.
   */
  public ChaosGameDescriptionFactoryException() {
    super("An error occurred while creating a ChaosGameDescription object.");
  }

  /**
   * Constructs a new ChaosGameDescriptionFactoryException with the specified message and cause.
   *
   * @param message the message
   * @param cause the cause
   */
  public ChaosGameDescriptionFactoryException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new ChaosGameDescriptionFactoryException with the specified message.
   *
   * @param message the message
   */
  public ChaosGameDescriptionFactoryException(String message) {
    super(message);
  }
}
