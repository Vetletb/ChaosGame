package edu.ntnu.idatt2003.model.game;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2003.exceptions.EmptyListException;
import edu.ntnu.idatt2003.exceptions.InvalidPositiveIntException;
import edu.ntnu.idatt2003.exceptions.InvalidSignException;
import edu.ntnu.idatt2003.exceptions.InvalidTypeException;
import edu.ntnu.idatt2003.exceptions.InvalidVectorRangeException;
import edu.ntnu.idatt2003.exceptions.IsNullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Test for ChaosGame. Has both positive and negative tests.
 */
class ChaosGameTest {

  @Nested
  @DisplayName("Positive tests")
  class MethodsReturnsCorrectValues {

    @Test
    @DisplayName("Constructor creates a ChaosGame object")
    void constructorDoesNotThrowExceptionOnValidParameters()
        throws InvalidSignException, IsNullException,
        EmptyListException, InvalidVectorRangeException, InvalidTypeException,
        InvalidPositiveIntException {
      ChaosGameDescription description = ChaosGameDescriptionFactory.get("Sierpinski");
      ChaosGame chaosGame = new ChaosGame(description, 100, 100);
      assertNotNull(chaosGame);
    }

    @Nested
    @DisplayName("Setup a ChaosGame object for testing")
    class SetupChaosGame {
      ChaosGame chaosGame;

      @BeforeEach
      void setUp() throws InvalidSignException, IsNullException,
          EmptyListException, InvalidVectorRangeException, InvalidTypeException,
          InvalidPositiveIntException {
        ChaosGameDescription description = ChaosGameDescriptionFactory.get("Sierpinski");
        chaosGame = new ChaosGame(description, 100, 100);
      }

      @Test
      @DisplayName("resetGame does not throw exception")
      void resetGameDoesNotThrowException() {
        assertDoesNotThrow(chaosGame::resetGame);
      }

      @Test
      @DisplayName("resetGameWithNewDescription does not throw exception on valid parameters")
      void resetGameWithNewDescriptionDoesNotThrowException()
          throws InvalidSignException, IsNullException,
          EmptyListException, InvalidVectorRangeException, InvalidTypeException {
        ChaosGameDescription description = ChaosGameDescriptionFactory.get("Julia Set");
        assertDoesNotThrow(() -> chaosGame.resetGameWithDescription(description));
      }

      @Test
      @DisplayName("runSteps does not throw exception on valid parameters")
      void runStepsDoesNotThrowException() {
        assertDoesNotThrow(() -> chaosGame.runSteps(1000));
      }
    }
  }

  @Nested
  @DisplayName("Negative tests")
  class MethodsThrowsExceptionOnInvalidParameters {

    @Test
    @DisplayName("Constructor throws IsNullException on null description")
    void constructorThrowsIsNullExceptionOnNullDescription() {
      assertThrows(IsNullException.class, () -> new ChaosGame(null, 100, 100));
    }

    @Test
    @DisplayName("resetGameWithNewDescription throws IsNullException on null description")
    void resetGameWithNewDescriptionThrowsIsNullExceptionOnNullDescription()
        throws InvalidSignException,
        IsNullException, EmptyListException, InvalidVectorRangeException, InvalidTypeException,
        InvalidPositiveIntException {
      ChaosGameDescription description = ChaosGameDescriptionFactory.get("Sierpinski");
      ChaosGame chaosGame = new ChaosGame(description, 100, 100);
      assertThrows(IsNullException.class, () -> chaosGame.resetGameWithDescription(null));
    }

    @Test
    @DisplayName("runSteps throws InvalidPositiveIntException on negative steps")
    void runStepsThrowsInvalidPositiveIntExceptionOnNegativeSteps()
        throws InvalidSignException, IsNullException, EmptyListException,
        InvalidVectorRangeException, InvalidTypeException, InvalidPositiveIntException {
      ChaosGameDescription description = ChaosGameDescriptionFactory.get("Sierpinski");
      ChaosGame chaosGame = new ChaosGame(description, 100, 100);
      assertThrows(InvalidPositiveIntException.class, () -> chaosGame.runSteps(0));
    }
  }
}