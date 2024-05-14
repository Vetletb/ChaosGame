package edu.ntnu.idatt2003.exceptions;

public class ChaosGameDescriptionFactoryException extends Exception {
  public ChaosGameDescriptionFactoryException() {
    super("An error occurred while creating a ChaosGameDescription object.");
  }

  public ChaosGameDescriptionFactoryException(String message, Throwable cause) {
    super(message, cause);
  }

  public ChaosGameDescriptionFactoryException(String message) {
    super(message);
  }
}
