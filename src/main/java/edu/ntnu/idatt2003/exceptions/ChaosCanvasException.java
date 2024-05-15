package edu.ntnu.idatt2003.exceptions;

/**
 * Exception class for ChaosCanvas.
 */
public class ChaosCanvasException extends Exception {
  /**
   * Constructs a new ChaosCanvasException with the default message.
   */
  public ChaosCanvasException() {
    super("Chaos canvas exception occurred.");
  }

  /**
   * Constructs a new ChaosCanvasException with the specified message and cause.
   *
   * @param message the message
   * @param cause the cause
   */
  public ChaosCanvasException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new ChaosCanvasException with the specified message.
   *
   * @param message the message
   */
  public ChaosCanvasException(String message) {
    super(message);
  }
}
