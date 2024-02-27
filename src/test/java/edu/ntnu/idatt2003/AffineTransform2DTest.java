package edu.ntnu.idatt2003;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

class AffineTransform2DTest {

  @Nested
  @DisplayName("Positive tests for AffineTransform2D, returns correct values")
  public class MethodsReturnsCorrectValues {

    @Nested
    @DisplayName("Positive tests for transform, returns correct values")
    public class TransformReturnsCorrectValues {
      @Test
      @DisplayName("Transform returns correct values, test 1")
      public void transformReturnsCorrectValues1() {
        Matrix2x2 matrix2x2 = new Matrix2x2(1, 0, 0, 1);
        Vector2D vector2D = new Vector2D(0, 0);
        AffineTransform2D affineTransform2D = new AffineTransform2D(matrix2x2, vector2D);
        Vector2D vector2D1 = new Vector2D(1, 1);
        Vector2D vector2D2 = affineTransform2D.transform(vector2D1);
        assertEquals(1, vector2D2.getX0());
        assertEquals(1, vector2D2.getX1());
      }

      @Test
      @DisplayName("Transform returns correct values, test 2")
      public void transformReturnsCorrectValues2() {
        Matrix2x2 matrix2x2 = new Matrix2x2(-1, 2, 3, -1);
        Vector2D vector2D = new Vector2D(4, -1);
        AffineTransform2D affineTransform2D = new AffineTransform2D(matrix2x2, vector2D);
        Vector2D vector2D1 = new Vector2D(4, 2);
        Vector2D vector2D2 = affineTransform2D.transform(vector2D1);
        assertEquals(4, vector2D2.getX0());
        assertEquals(9, vector2D2.getX1());
      }

      @Test
      @DisplayName("Transform returns correct values, test 3")
      public void transformReturnsCorrectValues3() {
        Matrix2x2 matrix2x2 = new Matrix2x2(3, 5, 2, 0);
        Vector2D vector2D = new Vector2D(-5, 0);
        AffineTransform2D affineTransform2D = new AffineTransform2D(matrix2x2, vector2D);
        Vector2D vector2D1 = new Vector2D(-3, -1);
        Vector2D vector2D2 = affineTransform2D.transform(vector2D1);
        assertEquals(-19, vector2D2.getX0());
        assertEquals(-6, vector2D2.getX1());
      }
    }
  }
}