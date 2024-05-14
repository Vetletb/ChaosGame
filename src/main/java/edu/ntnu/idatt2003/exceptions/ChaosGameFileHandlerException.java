package edu.ntnu.idatt2003.exceptions;

public class ChaosGameFileHandlerException extends Exception {
  public ChaosGameFileHandlerException() {
    super("An error occurred while handling the file");
  }

  public ChaosGameFileHandlerException(String message, Throwable cause) {
    super(message, cause);
  }

  public ChaosGameFileHandlerException(String message) {
    super(message);
  }
}
