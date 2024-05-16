package edu.ntnu.idatt2003.model.game;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2003.exceptions.EmptyListException;
import edu.ntnu.idatt2003.exceptions.InvalidSignException;
import edu.ntnu.idatt2003.exceptions.InvalidTypeException;
import edu.ntnu.idatt2003.exceptions.InvalidVectorRangeException;
import edu.ntnu.idatt2003.exceptions.IsNullException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Test for ChaosGameDescriptionFactory. Has both positive and negative tests.
 */
class ChaosGameDescriptionFactoryTest {

  @Nested
  @DisplayName("Positive tests")
  class MethodsReturnsCorrectDescription {
    @Test
    @DisplayName("get Sierpinski returns a ChaosGameDescription object")
    public void getSierpinskiReturnsCorrectDescription()
        throws InvalidSignException, IsNullException,
        EmptyListException, InvalidVectorRangeException, InvalidTypeException {
      ChaosGameDescription chaosGameDescription = ChaosGameDescriptionFactory.get("Sierpinski");
      assertNotNull(chaosGameDescription);
    }

    @Test
    @DisplayName("get Barnsley returns a ChaosGameDescription object")
    public void getBarnsleyReturnsCorrectDescription()
        throws InvalidSignException, IsNullException,
        EmptyListException, InvalidVectorRangeException, InvalidTypeException {
      ChaosGameDescription chaosGameDescription = ChaosGameDescriptionFactory.get("Barnsley");
      assertNotNull(chaosGameDescription);
    }

    @Test
    @DisplayName("get Julia Set returns a ChaosGameDescription object")
    public void getJuliaSetReturnsCorrectDescription()
        throws InvalidSignException, IsNullException,
        EmptyListException, InvalidVectorRangeException, InvalidTypeException {
      ChaosGameDescription chaosGameDescription = ChaosGameDescriptionFactory.get("Julia Set");
      assertNotNull(chaosGameDescription);
    }
  }

  @Nested
  @DisplayName("Negative tests")
  class MethodsThrowsException {
    @Test
    @DisplayName("get with invalid type throws InvalidTypeException")
    public void getWithInvalidTypeThrowsException() {
      assertThrows(InvalidTypeException.class, () -> ChaosGameDescriptionFactory.get("Invalid"));
    }
  }
}