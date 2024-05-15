package edu.ntnu.idatt2003.model.game;

import edu.ntnu.idatt2003.exceptions.ChaosGameDescriptionException;
import edu.ntnu.idatt2003.model.math.mathModel.Complex;
import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;
import edu.ntnu.idatt2003.model.math.transformation.JuliaTransform;
import edu.ntnu.idatt2003.model.math.transformation.Transform2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for ChaosGameDescription. Has both positive and negative tests.
 */
class ChaosGameDescriptionTest {
  @Nested
  @DisplayName("Positive tests")
  class MethodsReturnsCorrectValues {
    ChaosGameDescription description;
    List<Transform2D> transforms;
    Vector2D minCoords;
    Vector2D maxCoords;

    @BeforeEach
    public void setUp() throws ChaosGameDescriptionException {
      transforms = List.of(
          new JuliaTransform(new Complex(-0.74543, 0.11301), 1),
          new JuliaTransform(new Complex(-0.74543, 0.11301), -1)
      );
      minCoords = new Vector2D(-1.6, -1);
      maxCoords = new Vector2D(1.6, 1);
      description = new ChaosGameDescription(transforms, minCoords, maxCoords);
    }

    @Test
    @DisplayName("Test getTransforms method")
    public void testGetTransformsMethod() {
      assertEquals(transforms, description.getTransforms());
    }

    @Test
    @DisplayName("Test getMinCoords method")
    public void testGetMinCoordsMethod() {
      assertEquals(minCoords, description.getMinCoords());
    }

    @Test
    @DisplayName("Test getMaxCoords method")
    public void testGetMaxCoordsMethod() {
      assertEquals(maxCoords, description.getMaxCoords());
    }

    @Nested
    @DisplayName("Tests for equals")
    public class PositiveTestsForEquals {
      @Test
      @DisplayName("Equals returns true when two descriptions are equal")
      void equalsReturnsTrueWhenTwoDescriptionsAreEqual() throws ChaosGameDescriptionException {
        ChaosGameDescription description2 = new ChaosGameDescription(transforms, minCoords, maxCoords);
        assertEquals(description, description2);
      }

      @Test
      @DisplayName("Equals returns false when two descriptions are not equal")
      void equalsReturnsFalseWhenTwoDescriptionsAreNotEqual() throws ChaosGameDescriptionException {
        List<Transform2D> transforms2 = List.of(
            new JuliaTransform(new Complex(-0.7, 0.301), 1),
            new JuliaTransform(new Complex(-0.7, 0.301), -1)
        );
        Vector2D minCoords2 = new Vector2D(-2, -1);
        Vector2D maxCoords2 = new Vector2D(2, 1);
        ChaosGameDescription description2 = new ChaosGameDescription(transforms2, minCoords2, maxCoords2);
        assertNotEquals(description, description2);
      }

      @Test
      @DisplayName("Equals returns false when comparing a description to null")
      void equalsReturnsFalseWhenComparingADescriptionToNull() {
        assertNotEquals(description, null);
      }

      @Test
      @DisplayName("Equals returns false when comparing a description to a different object")
      void equalsReturnsFalseWhenComparingADescriptionToADifferentObject() {
        assertNotEquals(description, new Object());
      }
    }
  }

  @Nested
  @DisplayName("Negative tests")
  class MethodsThrowsExceptions {
    @Test
    @DisplayName("Test constructor throws ChaosGameDescriptionException if transforms is null")
    public void testConstructorTransformsIsNull() {
      assertThrows(ChaosGameDescriptionException.class, () -> new ChaosGameDescription(
          null, new Vector2D(0, 0), new Vector2D(1, 1)));
    }

    @Test
    @DisplayName("Test constructor throws ChaosGameDescriptionException if transforms is empty")
    public void testConstructorTransformsIsEmpty() {
      assertThrows(ChaosGameDescriptionException.class, () -> new ChaosGameDescription(
          List.of(), new Vector2D(0, 0), new Vector2D(1, 1)));
    }

    @Test
    @DisplayName("Test constructor throws ChaosGameDescriptionException if minCoords is null")
    public void testConstructorIfMinCoordsIsNull() {
      assertThrows(ChaosGameDescriptionException.class, () -> new ChaosGameDescription(
          List.of(new JuliaTransform(new Complex(0, 0), 1)), null, new Vector2D(1, 1)));
    }

    @Test
    @DisplayName("Test constructor throws ChaosGameDescriptionException if maxCoords is null")
    public void testConstructorMaxCoordsIsNull() {
      assertThrows(ChaosGameDescriptionException.class, () -> new ChaosGameDescription(
          List.of(new JuliaTransform(new Complex(0, 0), 1)), new Vector2D(0, 0), null));
    }

    @Test
    @DisplayName("Test constructor throws ChaosGameDescriptionException if minCoords is greater than maxCoords")
    public void testConstructorMinCoordsIsGreaterThanMaxCoords() {
      assertThrows(ChaosGameDescriptionException.class, () -> new ChaosGameDescription(
          List.of(new JuliaTransform(new Complex(0, 0), 1)), new Vector2D(1, 1), new Vector2D(0, 0)));
    }
  }
}