package edu.ntnu.idatt2003.exceptions;

public class WrongFileFormatException extends Exception {
  public WrongFileFormatException() {
    super("The file has the wrong format");
  }

  public WrongFileFormatException(String message) {
    super(message);
  }
}
