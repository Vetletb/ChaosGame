package edu.ntnu.idatt2003.model.math.transformation;

import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;

/**
 * An interface for transforming 2D vectors.
 */
public interface Transform2D {

  /**
   * Abstract method for transforming a 2D vector.
   */
  Vector2D transform(Vector2D point);
}
