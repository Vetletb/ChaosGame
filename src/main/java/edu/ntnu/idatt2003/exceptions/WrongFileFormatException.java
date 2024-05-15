package edu.ntnu.idatt2003.exceptions;

/**
 * Exception class for ChaosGameFileHandler.
 */
public class WrongFileFormatException extends ChaosGameFileHandlerException {
  /**
   * Constructs a new WrongFileFormatException with the default message.
   */
  public WrongFileFormatException() {
    super("The file has the wrong format");
  }

  /**
   * Constructs a new WrongFileFormatException with the specified message and cause.
   *
   * @param message the message
   * @param cause the cause
   */
  public WrongFileFormatException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new WrongFileFormatException with the specified message.
   *
   * @param message the message
   */
  public WrongFileFormatException(String message) {
    super(message);
  }
}
