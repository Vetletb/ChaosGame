package edu.ntnu.idatt2003;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class Vector2DTest {

  @Nested
  @DisplayName("Positive tests for Vector2D, returns correct values")
  public class MethodsReturnsCorrectValues {

    @Nested
    @DisplayName("Positive tests for add, returns correct values")
    public class AddReturnsCorrectValues {
      @Test
      @DisplayName("Add returns correct values, test 1")
      void addReturnsCorrectValues1() {
        Vector2D vector1 = new Vector2D(5, 2);
        Vector2D vector2 = new Vector2D(4, 1);
        Vector2D vector3 = vector1.add(vector2);
        assertEquals(9, vector3.getX0());
        assertEquals(3, vector3.getX1());
      }

      @Test
      @DisplayName("Add returns correct values, test 2")
      void addReturnsCorrectValues2() {
        Vector2D vector1 = new Vector2D(-1, 2);
        Vector2D vector2 = new Vector2D(3, -4);
        Vector2D vector3 = vector1.add(vector2);
        assertEquals(2, vector3.getX0());
        assertEquals(-2, vector3.getX1());
      }

      @Test
      @DisplayName("Add returns correct values, test 3")
      void addReturnsCorrectValues3() {
        Vector2D vector1 = new Vector2D(0, 0);
        Vector2D vector2 = new Vector2D(0, 0);
        Vector2D vector3 = vector1.add(vector2);
        assertEquals(0, vector3.getX0());
        assertEquals(0, vector3.getX1());
      }
    }

    @Nested
    @DisplayName("Positive tests for subtract, returns correct values")
    public class SubtractReturnsCorrectValues {
      @Test
      @DisplayName("Subtract returns correct values, test 1")
      void subtractReturnsCorrectValues1() {
        Vector2D vector1 = new Vector2D(5, 2);
        Vector2D vector2 = new Vector2D(4, 1);
        Vector2D vector3 = vector1.subtract(vector2);
        assertEquals(1, vector3.getX0());
        assertEquals(1, vector3.getX1());
      }

      @Test
      @DisplayName("Subtract returns correct values, test 2")
      void subtractReturnsCorrectValues2() {
        Vector2D vector1 = new Vector2D(-1, -2);
        Vector2D vector2 = new Vector2D(-3, 4);
        Vector2D vector3 = vector1.subtract(vector2);
        assertEquals(2, vector3.getX0());
        assertEquals(-6, vector3.getX1());
      }

      @Test
      @DisplayName("Subtract returns correct values, test 3")
      void subtractReturnsCorrectValues3() {
        Vector2D vector1 = new Vector2D(0, 0);
        Vector2D vector2 = new Vector2D(3, 0);
        Vector2D vector3 = vector1.subtract(vector2);
        assertEquals(-3, vector3.getX0());
        assertEquals(0, vector3.getX1());
      }
    }

    @Nested
    @DisplayName("Positive tests for scale, returns correct values")
    public class ScaleReturnsCorrectValues {
      @Test
      @DisplayName("Scale returns correct values, test 1")
      void scaleReturnsCorrectValues1() {
        Vector2D vector1 = new Vector2D(5, 2);
        Vector2D vector2 = vector1.scale(3);
        assertEquals(15, vector2.getX0());
        assertEquals(6, vector2.getX1());
      }

      @Test
      @DisplayName("Scale returns correct values, test 2")
      void scaleReturnsCorrectValues2() {
        Vector2D vector1 = new Vector2D(-1, -2);
        Vector2D vector2 = vector1.scale(4);
        assertEquals(-4, vector2.getX0());
        assertEquals(-8, vector2.getX1());
      }

      @Test
      @DisplayName("Scale returns correct values, test 3")
      void scaleReturnsCorrectValues3() {
        Vector2D vector1 = new Vector2D(0, 0);
        Vector2D vector2 = vector1.scale(3);
        assertEquals(0, vector2.getX0());
        assertEquals(0, vector2.getX1());
      }
    }
  }
}