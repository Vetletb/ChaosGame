package edu.ntnu.idatt2003.model.math.mathModel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Matrix2x2Test {

  @Nested
  @DisplayName("Positive tests")
  class MethodsReturnsCorrectValues {

    @Nested
    @DisplayName("Positive tests for the multiply method")
    public class PositiveTestsForMultiplyMethod {

      @Test
      @DisplayName("Test multiply method with a 2x2 matrix and a 2D vector, Test 1")
      public void testMultiplyMethod() {
        Matrix2x2 matrix = new Matrix2x2(1, 2, 3, 4);
        Vector2D vector = new Vector2D(1, 2);
        Vector2D result = matrix.multiply(vector);
        assertEquals(5, result.getX0());
        assertEquals(11, result.getX1());
      }

      @Test
      @DisplayName("Test multiply method with a 2x2 matrix and a 2D vector, Test 2")
      public void testMultiplyMethod2() {
        Matrix2x2 matrix = new Matrix2x2(1, 2, 3, 4);
        Vector2D vector = new Vector2D(3, 4);
        Vector2D result = matrix.multiply(vector);
        assertEquals(11, result.getX0());
        assertEquals(25, result.getX1());
      }

      @Test
      @DisplayName("Test multiply method with a 2x2 matrix and a 2D vector, Test 3")
      public void testMultiplyMethod3() {
        Matrix2x2 matrix = new Matrix2x2(1, 3, 3, 8);
        Vector2D vector = new Vector2D(1, 2);
        Vector2D result = matrix.multiply(vector);
        assertEquals(7, result.getX0());
        assertEquals(19, result.getX1());
      }
    }

    @Nested
    @DisplayName("Tests for equals")
    public class PositiveTestsForEquals {
      @Test
      @DisplayName("Equals returns true when two matrix's are equal")
      void equalsReturnsTrueWhenTwoMatrixsAreEqual() {
        Matrix2x2 matrix1 = new Matrix2x2(1, 2, 3, 4);
        Matrix2x2 matrix2 = new Matrix2x2(1, 2, 3, 4);
        assertEquals(matrix1, matrix2);
      }

      @Test
      @DisplayName("Equals returns false when two matrix's are not equal")
      void equalsReturnsFalseWhenTwoMatrixsAreNotEqual() {
        Matrix2x2 matrix1 = new Matrix2x2(1, 2, 3, 4);
        Matrix2x2 matrix2 = new Matrix2x2(4, 3, 2, 1);
        assertNotEquals(matrix1, matrix2);
      }

      @Test
      @DisplayName("Equals returns false when comparing a matrix to null")
      void equalsReturnsFalseWhenComparingAMatrixToNull() {
        Matrix2x2 matrix = new Matrix2x2(1, 2, 3, 4);
        assertNotEquals(matrix, null);
      }

      @Test
      @DisplayName("Equals returns false when comparing a matrix to a different object")
      void equalsReturnsFalseWhenComparingAMatrixToADifferentObject() {
        Matrix2x2 matrix = new Matrix2x2(1, 2, 3, 4);
        assertNotEquals(matrix, new Object());
      }
    }
  }
}