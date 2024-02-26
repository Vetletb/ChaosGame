package edu.ntnu.idatt2003;

/**
 * A class representing an affine transformation in 2D.
 */
public class AffineTransform2D implements Transform2D {
  private final Matrix2x2 matrix;
  private final Vector2D vector;

  /**
   * Constructor for the AffineTransform2D class.
   */
  public AffineTransform2D(Matrix2x2 matrix, Vector2D vector) {
    this.matrix = matrix;
    this.vector = vector;
  }

  /**
   * Method for the affine transformation of a 2D vector.
   *
   * @param point the vector to transform.
   *
   * @return the transformed vector.
   */
  @Override
  public Vector2D transform(Vector2D point) {
    Vector2D ax = matrix.multiply(point);
    return vector.add(ax);
  }
}
