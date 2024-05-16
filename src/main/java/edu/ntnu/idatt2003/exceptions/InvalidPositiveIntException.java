package edu.ntnu.idatt2003.exceptions;

/**
 * Exception class for invalid positive integers.
 */
public class InvalidPositiveIntException extends Exception {
  /**
   * Constructs a new InvalidPositiveIntException with the default message.
   */
  public InvalidPositiveIntException() {
    super("Invalid positive integer exception occurred.");
  }

  /**
   * Constructs a new InvalidPositiveIntException with the specified message and cause.
   *
   * @param message the message
   * @param cause   the cause
   */
  public InvalidPositiveIntException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new InvalidPositiveIntException with the specified message.
   *
   * @param message the message
   */
  public InvalidPositiveIntException(String message) {
    super(message);
  }
}