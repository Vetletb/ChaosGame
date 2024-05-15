package edu.ntnu.idatt2003.util;

import java.util.List;

/**
 * A class for input validation on common parameters used in the project.
 * Checks if the input is valid, and throws an IllegalArgumentException if not.
 */
public class InputValidation {

  /**
   * Validates that an object is not null.
   *
   * @param object to be validated
   * @param parameterName the name of the object
   * @throws IllegalArgumentException if the object is null
   */
  public static void validateNotNull(Object object, String parameterName)
      throws IllegalArgumentException {
    if (object == null) {
      throw new IllegalArgumentException("Object: " + parameterName + " cannot be null");
    }
  }

  /**
   * Validates that an int is positive.
   *
   * @param number to be validated
   * @param parameterName the name of the int
   * @throws IllegalArgumentException if the int is not positive
   */
  public static void validatePositiveInt(int number, String parameterName)
      throws IllegalArgumentException {
    if (number <= 0) {
      throw new IllegalArgumentException("Number: " + parameterName + " must be positive");
    }
  }

  /**
   * Validates that a list is not empty.
   *
   * @param list to be validated
   * @param parameterName the name of the list
   * @throws IllegalArgumentException if the list is empty
   */
  public static <T> void validateListNotEmpty(List<T> list, String parameterName)
      throws IllegalArgumentException {
    if (list.isEmpty()) {
      throw new IllegalArgumentException("List: " + parameterName + " must not be empty");
    }
  }
}
