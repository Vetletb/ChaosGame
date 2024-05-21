package edu.ntnu.idatt2003.util;

import edu.ntnu.idatt2003.exceptions.EmptyListException;
import edu.ntnu.idatt2003.exceptions.InvalidPositiveIntException;
import edu.ntnu.idatt2003.exceptions.InvalidVectorRangeException;
import edu.ntnu.idatt2003.exceptions.IsNullException;
import edu.ntnu.idatt2003.exceptions.WrongLengthException;
import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;
import java.util.List;

/**
 * A class for input validation on common parameters used in the project.
 * Checks if the input is valid, and throws an IllegalArgumentException if not.
 */
public class InputValidation {

  /**
   * Private constructor to prevent instantiation of the class.
   */
  private InputValidation() {
  }

  /**
   * Validates that an object is not null.
   *
   * @param object to be validated
   * @param parameterName the name of the object
   * @throws IsNullException if the object is null
   */
  public static void validateNotNull(Object object, String parameterName)
      throws IsNullException {
    if (object == null) {
      throw new IsNullException("Object: " + parameterName + " cannot be null");
    }
  }

  /**
   * Validates that an int is positive.
   *
   * @param number to be validated
   * @param parameterName the name of the int
   * @throws InvalidPositiveIntException if the int is not positive
   */
  public static void validatePositiveInt(int number, String parameterName)
      throws InvalidPositiveIntException {
    if (number <= 0) {
      throw new InvalidPositiveIntException("Number: " + parameterName + " must be positive");
    }
  }

  /**
   * Validates that a list is not empty.
   *
   * @param list to be validated
   * @param parameterName the name of the list
   * @throws EmptyListException if the list is empty
   */
  public static <T> void validateListNotEmpty(List<T> list, String parameterName)
      throws EmptyListException {
    if (list.isEmpty()) {
      throw new EmptyListException("List: " + parameterName + " must not be empty");
    }
  }

  /**
   * Validates that a list is less or equal to a given size.
   *
   * @param list to be validated
   * @param size the maximum size of the list
   * @param parameterName the name of the list
   * @throws WrongLengthException if the list is bigger than the given size
   */
  public static void validateListLessOrEqualThan(List<?> list, int size, String parameterName)
      throws WrongLengthException {
    if (list.size() <= size) {
      throw new WrongLengthException("List: " + parameterName
          + " must have at least " + size + " elements");
    }
  }

  /**
   * Validates that a list is exactly a given size.
   *
   * @param list to be validated
   * @param size the size of the list
   * @param parameterName the name of the list
   * @throws WrongLengthException if the list is not the given size
   */
  public static void validateListLength(List<?> list, int size, String parameterName)
      throws WrongLengthException {
    if (list.size() != size) {
      throw new WrongLengthException("List: " + parameterName
          + " must have exactly " + size + " elements");
    }
  }

  /**
   * Validates that the minimum coordinates are less than the maximum coordinates.
   *
   * @param minCoords the minimum coordinates
   * @param maxCoords the maximum coordinates
   * @throws InvalidVectorRangeException if the minimum coordinates are greater than or equal to the
   *     maximum coordinates
   */
  public static void validateVectorRange(Vector2D minCoords, Vector2D maxCoords)
      throws InvalidVectorRangeException {
    if (minCoords.getX0() >= maxCoords.getX0() || minCoords.getX1() >= maxCoords.getX1()) {
      throw new InvalidVectorRangeException(
          "Minimum coordinates must be less than maximum coordinates");
    }
  }
}
