package edu.ntnu.idatt2003.model.game;

import edu.ntnu.idatt2003.exceptions.InvalidPositiveIntException;
import edu.ntnu.idatt2003.exceptions.InvalidVectorRangeException;
import edu.ntnu.idatt2003.exceptions.IsNullException;
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
   * @throws IsNullException if the description is null.
   * @throws InvalidPositiveIntException if the width or height are not positive.
   * @throws InvalidVectorRangeException if the minimum coordinates are greater than or equal to the
   *      maximum coordinates.
   */
  public ChaosGame(ChaosGameDescription description, int width, int height)
      throws IsNullException, InvalidPositiveIntException,
      InvalidVectorRangeException {
    InputValidation.validateNotNull(description, "description");

    this.canvas = new ChaosCanvas(
        width, height, description.getMinCoords(), description.getMaxCoords());
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
   * Gets the description of the chaos game.
   *
   * @return the description of the chaos game.
   */
  public ChaosGameDescription getDescriptions() {
    return description;
  }

  /**
   * Sets the description of the chaos game.
   *
   * @param description the description of the chaos game.
   * @throws IsNullException if the description is null.
   * @throws InvalidVectorRangeException if the minimum coordinates are greater than or equal to the
   *     maximum coordinates.
   */
  private void setDescription(ChaosGameDescription description)
      throws IsNullException, InvalidVectorRangeException {
    InputValidation.validateNotNull(description, "description");

    canvas.setMinMaxCoords(description.getMinCoords(), description.getMaxCoords());
    this.description = description;
    notifyObservers("setDescription");
  }

  /**
   * Resets the canvas in the chaos game.
   */
  public void resetGame() {
    canvas.clear();
    currentPoint = new Vector2D(0, 0);
    super.notifyObservers("clearGame");
  }

  /**
   * Resets the canvas and sets new description for the chaos game.
   *
   * @param description the description to reset the chaos game with.
   * @throws IsNullException if the description is null.
   * @throws InvalidVectorRangeException if the minimum coordinates are greater than or equal to the
   */
  public void resetGameWithDescription(ChaosGameDescription description)
      throws IsNullException, InvalidVectorRangeException {
    setDescription(description);
    resetGame();
  }

  /**
   * Runs the chaos game for the given number of steps.
   *
   * @param steps the number of steps to run the chaos game for.
   * @throws InvalidPositiveIntException if the number of steps is not positive.
   */
  public void runSteps(int steps) throws InvalidPositiveIntException {
    InputValidation.validatePositiveInt(steps, "steps");

    List<Transform2D> transforms = description.getTransforms();
    for (int i = 0; i < steps; i++) {
      Transform2D currentTransformation = transforms.get(random.nextInt(transforms.size()));
      currentPoint = currentTransformation.transform(currentPoint);
      canvas.putPixel(currentPoint);
      super.notifyObservers("putPixel");
    }
  }
}
