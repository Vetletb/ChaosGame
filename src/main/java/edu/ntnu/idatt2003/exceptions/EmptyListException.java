package edu.ntnu.idatt2003.exceptions;

/**
 * Exception class for empty lists.
 */
public class EmptyListException extends Exception {
  /**
   * Constructs a new EmptyListException with the default message.
   */
  public EmptyListException() {
    super("Empty list exception occurred.");
  }

  /**
   * Constructs a new EmptyListException with the specified message and cause.
   *
   * @param message the message
   * @param cause the cause
   */
  public EmptyListException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new EmptyListException with the specified message.
   *
   * @param message the message
   */
  public EmptyListException(String message) {
    super(message);
  }
}
