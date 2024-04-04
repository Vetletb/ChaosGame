package edu.ntnu.idatt2003.model.game;

import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;
import edu.ntnu.idatt2003.model.math.transformation.Transform2D;
import java.util.List;

/**
 * A class representing a chaos game description.
 */
public class ChaosGameDescription {
  private final List<Transform2D> transforms;
  private final Vector2D minCoords;
  private final Vector2D maxCoords;

  /**
   * Constructor for the ChaosGameDescription class.
   */
  public ChaosGameDescription(List<Transform2D> transforms, Vector2D minCoords, Vector2D maxCoords) {
    validateCoordinates(minCoords, maxCoords);
    this.transforms = transforms;
    this.minCoords = minCoords;
    this.maxCoords = maxCoords;
  }

  /**
   * Returns the list of transforms.
   *
   * @return the list of transforms.
   */
  public List<Transform2D> getTransforms() {
      return transforms;
    }

  /**
   * Returns the minimum coordinates.
   *
   * @return the minimum coordinates.
   */
  public Vector2D getMinCoords() {
      return minCoords;
    }

  /**
   * Returns the maximum coordinates.
   *
   * @return the maximum coordinates.
   */
  public Vector2D getMaxCoords() {
      return maxCoords;
    }

  private void validateCoordinates(Vector2D minCoords, Vector2D maxCoords) {
    if (minCoords.getX0() >= maxCoords.getX0() || minCoords.getX1() >= maxCoords.getX1()) {
      throw new IllegalArgumentException("minCoords must be less than maxCoords");
    }
  }
}
