package edu.ntnu.idatt2003;

/**
 * A class representing a julia transformation.
 */
public class JuliaTransform  implements Transform2D {
  private final Complex point;
  private final int sign;

  /**
   * Constructor for the JuliaTransform class.
   *
   * sign should be 1 or -1.
   */
  public JuliaTransform(Complex point, int sign) {
    this.point = point;
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
    Vector2D vector = this.point.subtract(point);
    Complex complex = new Complex(vector.getX0(), vector.getX1());
    return complex.sqrt().scale(sign);
  }
}
