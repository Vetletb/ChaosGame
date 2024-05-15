package edu.ntnu.idatt2003.model.math.transformation;

import edu.ntnu.idatt2003.model.math.mathModel.Matrix2x2;
import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;

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
    Vector2D vector = new Vector2D(this.vector.getX0(), this.vector.getX1());
    return vector.add(ax);
  }

  /**
   * Gets the matrix.
   *
   * @return the matrix.
   */
  public Matrix2x2 getMatrix() {
    return matrix;
  }

  /**
   * Gets the vector.
   *
   * @return the vector.
   */
  public Vector2D getVector() {
    return vector;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    return matrix.equals(((AffineTransform2D) obj).getMatrix())
        && vector.equals(((AffineTransform2D) obj).getVector());
  }
}
