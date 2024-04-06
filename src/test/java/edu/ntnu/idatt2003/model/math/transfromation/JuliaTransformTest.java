package edu.ntnu.idatt2003.model.math.transfromation;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2003.model.math.mathModel.Complex;
import edu.ntnu.idatt2003.model.math.transformation.JuliaTransform;
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
      void transformReturnsCorrectValues1() {
        JuliaTransform juliaTransform = new JuliaTransform(new Complex(3, 2), 1);
        Vector2D vector = new Vector2D(4, 1);
        Vector2D vector2 = juliaTransform.transform(vector);
        assertEquals(1.098, vector2.getX0(), 1e-3);
        assertEquals(-0.455, vector2.getX1(), 1e-3);
      }

      @Test
      @DisplayName("Transform returns correct values, test 2")
      void transformReturnsCorrectValues2() {
        JuliaTransform juliaTransform = new JuliaTransform(new Complex(-2, 3), 1);
        Vector2D vector = new Vector2D(0, 3);
        Vector2D vector2 = juliaTransform.transform(vector);
        assertEquals(1.414, vector2.getX0(), 1e-3);
        assertEquals(0, vector2.getX1(), 1e-3);
      }

      @Test
      @DisplayName("Transform returns correct values, test 3")
      void transformReturnsCorrectValues3() {
        JuliaTransform juliaTransform = new JuliaTransform(new Complex(4, 5), 1);
        Vector2D vector = new Vector2D(2, 1);
        Vector2D vector2 = juliaTransform.transform(vector);
        assertEquals(1.112, vector2.getX0(), 1e-3);
        assertEquals(-1.799, vector2.getX1(), 1e-3);
      }
    }
  }

    @Nested
    @DisplayName("Negative tests for JuliaTransform, throws exceptions")
    public class MethodsThrowsExceptions {

      @Test
      @DisplayName("Constructor throws IllegalArgumentException if sign is not 1 or -1")
        void constructorThrowsIllegalArgumentException() {
          assertThrows(IllegalArgumentException.class, () -> new JuliaTransform(new Complex(3, 2), 0));
        }
    }
}