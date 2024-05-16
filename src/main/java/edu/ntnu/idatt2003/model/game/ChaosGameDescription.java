package edu.ntnu.idatt2003.model.game;

import edu.ntnu.idatt2003.exceptions.EmptyListException;
import edu.ntnu.idatt2003.exceptions.InvalidVectorRangeException;
import edu.ntnu.idatt2003.exceptions.IsNullException;
import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;
import edu.ntnu.idatt2003.model.math.transformation.Transform2D;
import edu.ntnu.idatt2003.util.InputValidation;
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
   *
   * @param transforms the list of transforms.
   * @param minCoords the minimum coordinates.
   * @param maxCoords the maximum coordinates.
   *
   * @throws IsNullException if any of the parameters are null.
   * @throws EmptyListException if the list of transforms is empty.
   * @throws InvalidVectorRangeException if the minimum coordinates are greater than or equal to the
   *      maximum coordinates.
   */
  public ChaosGameDescription(List<Transform2D> transforms, Vector2D minCoords, Vector2D maxCoords)
      throws IsNullException, EmptyListException, InvalidVectorRangeException {
    InputValidation.validateNotNull(transforms, "transforms");
    InputValidation.validateListNotEmpty(transforms, "transforms");
    InputValidation.validateNotNull(minCoords, "minCoords");
    InputValidation.validateNotNull(maxCoords, "maxCoords");
    InputValidation.validateVectorRange(minCoords, maxCoords);

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

  /**
   * Validates if the minimum coordinates are less than the maximum coordinates.
   *
   * @param minCoords the minimum coordinates.
   * @param maxCoords the maximum coordinates.
   *
   * @throws InvalidVectorRangeException if minCoords is greater than or equal to maxCoords
   */
  private void validateCoordinates(Vector2D minCoords, Vector2D maxCoords)
      throws InvalidVectorRangeException {
    if (minCoords.getX0() >= maxCoords.getX0() || minCoords.getX1() >= maxCoords.getX1()) {
      throw new InvalidVectorRangeException("minCoords must be less than maxCoords");
    }
  }

  /**
   * Compares this object to another object.
   *
   * @param obj the object to compare to.
   * @return true if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    return transforms.equals(((ChaosGameDescription) obj).getTransforms())
        && minCoords.equals(((ChaosGameDescription) obj).getMinCoords())
        && maxCoords.equals(((ChaosGameDescription) obj).getMaxCoords());
  }
}
