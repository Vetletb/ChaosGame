package edu.ntnu.idatt2003.exceptions;

/**
 * Exception class for ChaosGame.
 */
public class ChaosGameException extends Exception {
  /**
   * Constructs a new ChaosGameException with the default message.
   */
  public ChaosGameException() {
    super("Chaos game exception occurred.");
  }

  /**
   * Constructs a new ChaosGameException with the specified message and cause.
   *
   * @param message the message
   * @param cause the cause
   */
  public ChaosGameException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new ChaosGameException with the specified message.
   *
   * @param message the message
   */
  public ChaosGameException(String message) {
    super(message);
  }
}
