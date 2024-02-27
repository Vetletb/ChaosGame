package edu.ntnu.idatt2003;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexTest {
  @Nested
  @DisplayName("Positive tests for the methods")
  class MethodsReturnsCorrectValues {


    @Nested
    @DisplayName("Positive tests for the sqrt method")
    public class PositiveTestsForSqrtMethod {

      @Test
      @DisplayName("Test sqrt method with a complex number, Test 1")
      public void testSqrtMethod() {
        Complex complex = new Complex(1, 2);
        Complex result = complex.sqrt();
        assertEquals(1.272, result.getX0(), 1e-3);
        assertEquals(0.786, result.getX1(), 1e-3);
      }

      @Test
      @DisplayName("Test sqrt method with a complex number, Test 2")
      public void testSqrtMethod2() {
        Complex complex = new Complex(3, 4);
        Complex result = complex.sqrt();
        assertEquals(2.0, result.getX0(), 1e-3);
        assertEquals(1.0, result.getX1(), 1e-3);
      }

      @Test
      @DisplayName("Test sqrt method with a complex number, Test 3")
      public void testSqrtMethod3() {
        Complex complex = new Complex(7, 0);
        Complex result = complex.sqrt();
        assertEquals(2.646, result.getX0(), 1e-3);
        assertEquals(0, result.getX1(), 1e-3);
      }
    }
  }
}