package edu.ntnu.idatt2003.model.math.transformation;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2003.exceptions.InvalidSignException;
import edu.ntnu.idatt2003.model.math.mathModel.Complex;
import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class JuliaTransformTest {

  @Nested
  @DisplayName("Positive tests for JuliaTransform, returns correct values")
  public class MethodsReturnsCorrectValues {

    @Nested
    @DisplayName("Positive tests for transform, returns correct values")
    public class TransformReturnsCorrectValues {

      @Test
      @DisplayName("Transform returns correct values, test 1")
      void transformReturnsCorrectValues1() throws InvalidSignException {
        JuliaTransform juliaTransform = new JuliaTransform(new Complex(3, 2), 1);
        Vector2D vector = new Vector2D(4, 1);
        Vector2D vector2 = juliaTransform.transform(vector);
        assertEquals(1.098, vector2.getX0(), 1e-3);
        assertEquals(-0.455, vector2.getX1(), 1e-3);
      }

      @Test
      @DisplayName("Transform returns correct values, test 2")
      void transformReturnsCorrectValues2() throws InvalidSignException {
        JuliaTransform juliaTransform = new JuliaTransform(new Complex(-2, 3), 1);
        Vector2D vector = new Vector2D(0, 3);
        Vector2D vector2 = juliaTransform.transform(vector);
        assertEquals(1.414, vector2.getX0(), 1e-3);
        assertEquals(0, vector2.getX1(), 1e-3);
      }

      @Test
      @DisplayName("Transform returns correct values, test 3")
      void transformReturnsCorrectValues3() throws InvalidSignException {
        JuliaTransform juliaTransform = new JuliaTransform(new Complex(4, 5), 1);
        Vector2D vector = new Vector2D(2, 1);
        Vector2D vector2 = juliaTransform.transform(vector);
        assertEquals(1.112, vector2.getX0(), 1e-3);
        assertEquals(-1.799, vector2.getX1(), 1e-3);
      }
    }

    @Nested
    @DisplayName("Tests for equals")
    public class PositiveTestsForEquals {
      @Test
      @DisplayName("Equals returns true when two julia are equal")
      void equalsReturnsTrueWhenTwoJuliaAreEqual() throws InvalidSignException {
        Complex complex = new Complex(3, 2);
        JuliaTransform julia1 = new JuliaTransform(complex, 1);
        JuliaTransform julia2 = new JuliaTransform(complex, 1);
        assertEquals(julia1, julia2);
      }

      @Test
      @DisplayName("Equals returns false when two julia are not equal")
      void equalsReturnsFalseWhenTwoJuliaAreNotEqual() throws InvalidSignException {
        Complex complex1 = new Complex(3, 2);
        Complex complex2 = new Complex(4, 1);
        JuliaTransform julia1 = new JuliaTransform(complex1, 1);
        JuliaTransform julia2 = new JuliaTransform(complex2, -1);
        assertNotEquals(julia1, julia2);
      }

      @Test
      @DisplayName("Equals returns false when comparing a vector to null")
      void equalsReturnsFalseWhenComparingAVectorToNull() throws InvalidSignException {
        Complex complex = new Complex(3, 2);
        JuliaTransform julia = new JuliaTransform(complex, 1);
        assertNotEquals(julia, null);
      }

      @Test
      @DisplayName("Equals returns false when comparing a vector to a different object")
      void equalsReturnsFalseWhenComparingAVectorToADifferentObject() throws InvalidSignException {
        Complex complex = new Complex(3, 2);
        JuliaTransform julia = new JuliaTransform(complex, 1);
        assertNotEquals(julia, new Object());
      }
    }
  }

    @Nested
    @DisplayName("Negative tests for JuliaTransform, throws exceptions")
    public class MethodsThrowsExceptions {

      @Test
      @DisplayName("Constructor throws InvalidSignException if sign is not 1 or -1")
      void constructorThrowsInvalidSignException() {
        assertThrows(InvalidSignException.class, () -> new JuliaTransform(new Complex(3, 2), 0));
      }
    }
}