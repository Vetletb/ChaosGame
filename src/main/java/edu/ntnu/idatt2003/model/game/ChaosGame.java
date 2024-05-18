package edu.ntnu.idatt2003.model.game;

import edu.ntnu.idatt2003.exceptions.InvalidPositiveIntException;
import edu.ntnu.idatt2003.exceptions.InvalidVectorRangeException;
import edu.ntnu.idatt2003.exceptions.IsNullException;
import edu.ntnu.idatt2003.exceptions.PixelOutOfBoundsException;
import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;
import edu.ntnu.idatt2003.model.math.transformation.Transform2D;
import edu.ntnu.idatt2003.util.InputValidation;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * A class representing a chaos game. Utilized a canvas to draw points on, and a description of the
 * game to run the game.
 */
public class ChaosGame extends Subject {
  private final ChaosCanvas canvas;
  private ChaosGameDescription description;
  private Vector2D currentPoint;
  private int[] transformationProbabilities;
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
    setTransformationProbabilities();
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
    setTransformationProbabilities();
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

    for (int i = 0; i < steps; i++) {
      Transform2D currentTransformation = selectRandomTransformation();
      currentPoint = currentTransformation.transform(currentPoint);
      try {
        canvas.putPixel(currentPoint);
        super.notifyObservers("putPixel");
      } catch (PixelOutOfBoundsException e) {
        // Do nothing
      }
    }
  }

  /**
   * Selects a random transformation from the description based on the probabilities of each
   * transformation.
   *
   * @return the selected transformation.
   */
  private Transform2D selectRandomTransformation() {
    int choice = random.nextInt(101);
    int currentTransformationIndex = 0;
    for (int i = 0; i < transformationProbabilities.length; i++) {
      int difference = Math.abs(transformationProbabilities[i] - choice);
      if (difference < Math.abs(transformationProbabilities[currentTransformationIndex] - choice)) {
        currentTransformationIndex = i;
      }
    }
    return description.getTransforms().get(currentTransformationIndex);
  }

  /**
   * Selects a random number between 1 and 100 for each transformation in the description,
   * determining their likelihood of being chosen.
   */
  private void setTransformationProbabilities() {
    int numTransforms = description.getTransforms().size();
    transformationProbabilities = new int[numTransforms];
    for (int i = 0; i < numTransforms; i++) {
      transformationProbabilities[i] = random.nextInt(100) + 1;
    }
  }
}
