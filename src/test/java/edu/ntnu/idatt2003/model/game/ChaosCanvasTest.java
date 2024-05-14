package edu.ntnu.idatt2003.model.game;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2003.exceptions.ChaosCanvasException;
import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Test for ChaosCanvas. Has both positive and negative tests.
 */
class ChaosCanvasTest {

  @Nested
  @DisplayName("Positive tests")
  class MethodsReturnsCorrectValues {

    ChaosCanvas canvas;
    @BeforeEach
    public void setUp() throws ChaosCanvasException {
      canvas = new ChaosCanvas(100, 100, new Vector2D(0, 0), new Vector2D(200, 200));
    }

    @Nested
    @DisplayName("Positive tests for the getCanvasArray")
    public class PositiveTestsForGetCanvasArray {

      int[][] canvasArray;
      @BeforeEach
        public void setUp() {
        canvasArray = canvas.getCanvasArray();
      }

      @Test
      @DisplayName("getCanvasArray returns an array empty array")
      public void getCanvasArrayReturnsEmptyArray() {
        for (int[] ints : canvasArray) {
          for (int anInt : ints) {
            assertEquals(0, anInt);
          }
        }
      }

      @Test
      @DisplayName("getCanvasArray returns an array with the correct dimensions")
      public void getCanvasArrayReturnsArrayWithCorrectDimensions() {
        assertEquals(100, canvasArray.length);
        assertEquals(100, canvasArray[0].length);
      }
    }

    @Test
    @DisplayName("Positive tests for the putPixel")
    public void setPixelSetsCorrectPixel() {
      canvas.putPixel(new Vector2D(150, 50));
      int[][] canvasArray = canvas.getCanvasArray();
      for (int i = 0; i < canvasArray.length; i++) {
        for (int j = 0; j < canvasArray[i].length; j++) {
          if (i == 74 && j == 74) {
            assertEquals(1, canvasArray[i][j]);
          } else {
            assertEquals(0, canvasArray[i][j]);
          }
        }
      }
    }

    @Nested
    @DisplayName("Sets up a canvas")
    class SetUpCanvas {
      @BeforeEach
      public void setUp() {
        canvas.putPixel(new Vector2D(150, 50));
      }

      @Test
      @DisplayName("Positive tests for the getPixel")
      public void getPixelReturnsCorrectValue() {
        assertEquals(1, canvas.getPixel(new Vector2D(150, 50)));
        assertEquals(0, canvas.getPixel(new Vector2D(50, 150)));
        assertEquals(0, canvas.getPixel(new Vector2D(50, 50)));
      }

      @Test
      @DisplayName("Positive tests for the clear")
      public void clearSetsAllPixelsToZero() {
        canvas.clear();
        int[][] canvasArray = canvas.getCanvasArray();
        for (int[] ints : canvasArray) {
          for (int anInt : ints) {
            assertEquals(0, anInt);
          }
        }
      }
    }

    @Nested
    @DisplayName("Tests for equals")
    public class PositiveTestsForEquals {
      @Test
      @DisplayName("Equals returns true when two canvases are equal")
      void equalsReturnsTrueWhenTwoCanvasesAreEqual() throws ChaosCanvasException {
        ChaosCanvas canvas2 = new ChaosCanvas(100, 100, new Vector2D(0, 0), new Vector2D(200, 200));
        assertEquals(canvas, canvas2);
      }

      @Test
      @DisplayName("Equals returns false when two canvases are not equal")
      void equalsReturnsFalseWhenTwoCanvasesAreNotEqual() throws ChaosCanvasException {
        ChaosCanvas canvas2 = new ChaosCanvas(120, 120, new Vector2D(1, 1), new Vector2D(100, 100));
        assertNotEquals(canvas, canvas2);
      }

      @Test
      @DisplayName("Equals returns false when comparing a canvas to null")
      void equalsReturnsFalseWhenComparingACanvasToNull() {
        assertNotEquals(canvas, null);
      }

      @Test
      @DisplayName("Equals returns false when comparing a canvas to a different object")
      void equalsReturnsFalseWhenComparingACanvasToADifferentObject() {
        assertNotEquals(canvas, new Object());
      }
    }
  }

  @Nested
  @DisplayName("Negative tests")
  class MethodsThrowsException {

    @Test
    @DisplayName("Negative tests for setMinMaxCoords")
    public void setMinMaxCoordsThrowsExceptionOnNull() throws ChaosCanvasException {
      ChaosCanvas canvas = new ChaosCanvas(100, 100, new Vector2D(0, 0), new Vector2D(200, 200));
      assertThrows(ChaosCanvasException.class, () -> canvas.setMinMaxCoords(null, new Vector2D(200, 200)));
      assertThrows(ChaosCanvasException.class, () -> canvas.setMinMaxCoords(new Vector2D(0, 0), null));
    }

    @Nested
    @DisplayName("Constructor throws exception on invalid parameter")
    class ConstructorThrowsExceptions {

      @Test
      @DisplayName("Constructor throws exception on negative width")
      public void constructorThrowsExceptionOnNegativeWidth() {
        assertThrows(ChaosCanvasException.class, () -> new ChaosCanvas(-100, 100, new Vector2D(0, 0), new Vector2D(200, 200)));
      }

      @Test
      @DisplayName("Constructor throws exception on negative height")
      public void constructorThrowsExceptionOnNegativeHeight() {
        assertThrows(ChaosCanvasException.class, () -> new ChaosCanvas(100, -100, new Vector2D(0, 0), new Vector2D(200, 200)));
      }
    }
  }
}