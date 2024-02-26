package edu.ntnu.idatt2003;

/**
 * An interface for transforming 2D vectors.
 */
public interface Transform2D {

  /**
   * Abstract method for transforming a 2D vector.
   */
  public Vector2D transform(Vector2D point);
}
