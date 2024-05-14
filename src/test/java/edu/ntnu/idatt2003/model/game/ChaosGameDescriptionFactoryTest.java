package edu.ntnu.idatt2003.model.game;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idatt2003.exceptions.ChaosGameDescriptionFactoryException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ChaosGameDescriptionFactoryTest {

  @Nested
  @DisplayName("Positive tests")
  class MethodsReturnsCorrectDescription {
    @Test
    @DisplayName("get Sierpinski returns a ChaosGameDescription object")
    public void getSierpinskiReturnsCorrectDescription()
        throws ChaosGameDescriptionFactoryException {
      ChaosGameDescription chaosGameDescription = ChaosGameDescriptionFactory.get("Sierpinski");
      assertNotNull(chaosGameDescription);
    }

    @Test
    @DisplayName("get Barnsley returns a ChaosGameDescription object")
    public void getBarnsleyReturnsCorrectDescription()
        throws ChaosGameDescriptionFactoryException {
      ChaosGameDescription chaosGameDescription = ChaosGameDescriptionFactory.get("Barnsley");
      assertNotNull(chaosGameDescription);
    }

    @Test
    @DisplayName("get Julia Set returns a ChaosGameDescription object")
    public void getJuliaSetReturnsCorrectDescription()
        throws ChaosGameDescriptionFactoryException {
      ChaosGameDescription chaosGameDescription = ChaosGameDescriptionFactory.get("Julia Set");
      assertNotNull(chaosGameDescription);
    }
  }

  @Nested
  @DisplayName("Negative tests")
  class MethodsThrowsException {
    @Test
    @DisplayName("get with invalid type throws ChaosGameDescriptionFactoryException")
    public void getWithInvalidTypeThrowsException() {
      assertThrows(ChaosGameDescriptionFactoryException.class, () -> ChaosGameDescriptionFactory.get("Invalid"));
    }
  }
}