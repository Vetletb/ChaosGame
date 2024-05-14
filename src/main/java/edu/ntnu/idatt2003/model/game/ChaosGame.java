package edu.ntnu.idatt2003.model.game;

import edu.ntnu.idatt2003.exceptions.ChaosCanvasException;
import edu.ntnu.idatt2003.exceptions.ChaosGameException;
import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;
import edu.ntnu.idatt2003.model.math.transformation.Transform2D;
import edu.ntnu.idatt2003.util.InputValidation;
import java.util.List;
import java.util.Random;

/**
 * A class representing a chaos game.
 */
public class ChaosGame extends Subject {
  private final ChaosCanvas canvas;
  private ChaosGameDescription description;
  private Vector2D currentPoint;
  private final Random random;

  /**
   * Constructor for the ChaosGame class.
   *
   * @param description the description of the chaos game.
   * @param width the width of the canvas.
   * @param height the height of the canvas.
   */
  public ChaosGame(ChaosGameDescription description, int width, int height)
      throws ChaosGameException {
    try {
      InputValidation.validateNotNull(description, "description");
      this.canvas = new ChaosCanvas(
          width, height, description.getMinCoords(), description.getMaxCoords());
    } catch (IllegalArgumentException e) {
      throw new ChaosGameException("The description of the chaos game cannot be null.", e);
    } catch (ChaosCanvasException e) {
      throw new ChaosGameException("An error occurred while creating the ChaosCanvas class.", e);
    }
    this.description = description;
    this.currentPoint = new Vector2D(0, 0);
    this.random = new Random();
  }

  /**
   * Gets the canvas of the chaos game.
   *
   * @return the canvas of the chaos game.
   */
  public ChaosCanvas getCanvas() {
    return canvas;
  }

  /**
   * Sets the description of the chaos game.
   */
  private void setDescription(ChaosGameDescription description) throws ChaosGameException {
    try {
      InputValidation.validateNotNull(description, "description");
      canvas.setMinMaxCoords(description.getMinCoords(), description.getMaxCoords());
    } catch (IllegalArgumentException e) {
      throw new ChaosGameException("The description of the chaos game cannot be null.", e);
    } catch (ChaosCanvasException e) {
      throw new ChaosGameException(
          "An error occurred while setting the description of the chaos game.", e);
    }
    this.description = description;
  }

  /**
   * Resets the canvas in the chaos game.
   */
  public void resetGame() {
    canvas.clear();
    currentPoint = new Vector2D(0, 0);
  }

  /**
   * Resets the canvas and sets new description for the chaos game.
   *
   * @param description the description to reset the chaos game with.
   */
  public void resetGameWithDescription(ChaosGameDescription description) throws ChaosGameException {
    setDescription(description);
    resetGame();
  }

  /**
   * Runs the chaos game for the given number of steps.
   *
   * @param steps the number of steps to run the chaos game for.
   */
  public void runSteps(int steps) throws ChaosGameException {
    try {
      InputValidation.validatePositiveInt(steps, "steps");
    } catch (IllegalArgumentException e) {
      throw new ChaosGameException("The number of steps must be positive.", e);
    }
    List<Transform2D> transforms = description.getTransforms();
    for (int i = 0; i < steps; i++) {
      Transform2D currentTransformation = transforms.get(random.nextInt(transforms.size()));
      currentPoint = currentTransformation.transform(currentPoint);
      canvas.putPixel(currentPoint);
      super.notifyObservers();
    }
  }
}
