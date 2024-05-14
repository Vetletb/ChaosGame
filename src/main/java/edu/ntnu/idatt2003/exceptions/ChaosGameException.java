package edu.ntnu.idatt2003.exceptions;

public class ChaosGameException extends Exception {
  public ChaosGameException() {
    super("Chaos game exception occurred.");
  }

  public ChaosGameException(String message, Throwable cause) {
    super(message, cause);
  }

  public ChaosGameException(String message) {
    super(message);
  }
}
