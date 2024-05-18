package edu.ntnu.idatt2003.exceptions;

/**
 * An exception thrown when a pixel is out of bounds.
 */
public class PixelOutOfBoundsException extends Exception {
  /**
   * Constructs a new PixelOutOfBoundsException with the default message.
   */
  public PixelOutOfBoundsException() {
    super("The pixel is out of bounds.");
  }

  /**
   * Constructs a new PixelOutOfBoundsException with the specified message and cause.
   *
   * @param message the message
   * @param cause the cause
   */
  public PixelOutOfBoundsException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new PixelOutOfBoundsException with the specified message.
   *
   * @param message the message
   */
  public PixelOutOfBoundsException(String message) {
    super(message);
  }
}
