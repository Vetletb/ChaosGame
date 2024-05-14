package edu.ntnu.idatt2003.exceptions;

public class WrongFileFormatException extends ChaosGameFileHandlerException {
  public WrongFileFormatException() {
    super("The file has the wrong format");
  }

  public WrongFileFormatException(String message, Throwable cause) {
    super(message, cause);
  }

  public WrongFileFormatException(String message) {
    super(message);
  }
}
