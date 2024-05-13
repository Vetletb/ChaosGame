package edu.ntnu.idatt2003.model.game;

import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;
import edu.ntnu.idatt2003.model.math.transformation.Transform2D;
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
  public ChaosGame(ChaosGameDescription description, int width, int height) {
    this.description = description;
    this.canvas = new ChaosCanvas(width, height, description.getMinCoords(), description.getMaxCoords());
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
  private void setDescription(ChaosGameDescription description) {
    this.description = description;
    canvas.setMinMaxCoords(description.getMinCoords(), description.getMaxCoords());
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
  public void resetGameWithDescription(ChaosGameDescription description) {
    setDescription(description);
    resetGame();
  }

  /**
   * Runs the chaos game for the given number of steps.
   *
   * @param steps the number of steps to run the chaos game for.
   */
  public void runSteps(int steps) {
    List<Transform2D> transforms = description.getTransforms();
    for (int i = 0; i < steps; i++) {
      Transform2D currentTransformation = transforms.get(random.nextInt(transforms.size()));
      currentPoint = currentTransformation.transform(currentPoint);
      canvas.putPixel(currentPoint);
      super.notifyObservers();
    }
  }
}
