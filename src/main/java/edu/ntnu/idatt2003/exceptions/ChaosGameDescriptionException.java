package edu.ntnu.idatt2003.exceptions;

public class ChaosGameDescriptionException extends Exception {
  public ChaosGameDescriptionException() {
    super("An error occurred in the ChaosGameDescription class.");
  }

  public ChaosGameDescriptionException(String message) {
    super(message);
  }

  public ChaosGameDescriptionException(String message, Throwable cause) {
    super(message, cause);
  }
}
