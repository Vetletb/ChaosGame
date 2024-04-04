package edu.ntnu.idatt2003.model.math.transformation;

import edu.ntnu.idatt2003.model.math.mathModel.Complex;
import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;

/**
 * A class representing a julia transformation.
 */
public class JuliaTransform  implements Transform2D {
  private final Complex point;
  private final int sign;

  /**
   * Constructor for the JuliaTransform class.
   * sign should be 1 or -1.
   *
   * @param point the point to transform around.
   * @param sign the sign of the transformation.
   *
   * @throws IllegalArgumentException if sign is not 1 or -1.
   */
  public JuliaTransform(Complex point, int sign) {
    this.point = point;
    if (sign != 1 && sign != -1) {
      throw new IllegalArgumentException("Sign must be 1 or -1");
    }
    this.sign = sign;
  }

  /**
   * Method for the julia transformation of a 2D vector.
   *
   * @param point the vector to transform.
   *
   * @return the transformed vector.
   */
  @Override
  public Vector2D transform(Vector2D point) {
    Vector2D vector = new Vector2D(this.point.getX0(), this.point.getX1());
    vector = point.subtract(vector);
    Complex complex = new Complex(vector.getX0(), vector.getX1());
    return complex.sqrt().scale(sign);
  }

  /**
   * Gets the point.
   *
   * @return the point.
   */
  public Complex getPoint() {
    return point;
  }
}
