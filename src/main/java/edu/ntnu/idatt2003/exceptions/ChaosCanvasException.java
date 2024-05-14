package edu.ntnu.idatt2003.exceptions;

public class ChaosCanvasException extends Exception {
  public ChaosCanvasException() {
    super("Chaos canvas exception occurred.");
  }

  public ChaosCanvasException(String message, Throwable cause) {
    super(message, cause);
  }

  public ChaosCanvasException(String message) {
    super(message);
  }
}
