package edu.ntnu.idatt2003.model.game;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2003.exceptions.ChaosCanvasException;
import edu.ntnu.idatt2003.exceptions.ChaosGameDescriptionException;
import edu.ntnu.idatt2003.exceptions.ChaosGameDescriptionFactoryException;
import edu.ntnu.idatt2003.exceptions.ChaosGameException;
import edu.ntnu.idatt2003.model.math.transformation.AffineTransform2D;
import edu.ntnu.idatt2003.model.math.transformation.Transform2D;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ChaosGameTest {

  @Nested
  @DisplayName("Positive tests")
  class MethodsReturnsCorrectValues {

    @Test
    @DisplayName("Constructor creates a ChaosGame object")
    void constructorDoesNotThrowExceptionOnValidParameters()
        throws ChaosGameDescriptionFactoryException, ChaosGameDescriptionException,
        ChaosGameException, ChaosCanvasException {
      ChaosGameDescription description = ChaosGameDescriptionFactory.get("Sierpinski");
      ChaosGame chaosGame = new ChaosGame(description, 100, 100);
      assertNotNull(chaosGame);
    }

    @Nested
    @DisplayName("Setup a ChaosGame object for testing")
    class SetupChaosGame {
      ChaosGame chaosGame;

      @BeforeEach
      void setUp() throws ChaosGameDescriptionFactoryException, ChaosGameDescriptionException,
        ChaosGameException, ChaosCanvasException {
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
        throws ChaosGameDescriptionFactoryException {
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
    @DisplayName("Constructor throws ChaosGameException on null description")
    void constructorThrowsChaosGameExceptionOnNullDescription() {
      assertThrows(ChaosGameException.class, () -> new ChaosGame(null, 100, 100));
    }

    @Test
    @DisplayName("resetGameWithNewDescription throws ChaosGameException on null description")
    void resetGameWithNewDescriptionThrowsChaosGameExceptionOnNullDescription()
      throws ChaosGameDescriptionFactoryException, ChaosGameException {
      ChaosGameDescription description = ChaosGameDescriptionFactory.get("Sierpinski");
      ChaosGame chaosGame = new ChaosGame(description, 100, 100);
      assertThrows(ChaosGameException.class, () -> chaosGame.resetGameWithDescription(null));
    }

    @Test
    @DisplayName("runSteps throws ChaosGameException on negative steps")
    void runStepsThrowsChaosGameExceptionOnNegativeSteps()
      throws ChaosGameDescriptionFactoryException,
      ChaosGameException {
      ChaosGameDescription description = ChaosGameDescriptionFactory.get("Sierpinski");
      ChaosGame chaosGame = new ChaosGame(description, 100, 100);
      assertThrows(ChaosGameException.class, () -> chaosGame.runSteps(0));
    }
  }
}