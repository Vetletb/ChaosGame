package edu.ntnu.idatt2003;

/**
 * A class representing a 2x2 matrix.
 */
public class Matrix2x2 {
  private double a00;
  private double a01;
  private double a10;
  private double a11;

  /**
   * Constructor for the Matrix2x2 class.
   */
  public Matrix2x2(double a00, double a01, double a10, double a11) {
    this.a00 = a00;
    this.a01 = a01;
    this.a10 = a10;
    this.a11 = a11;
  }

  /**
   * Multiplies this matrix with the given vector.
   */
  public Vector2D multiply(Vector2D vector) {
    double x0 = a00 * vector.getX0() + a01 * vector.getX1();
    double x1 = a10 * vector.getX0() + a11 * vector.getX1();
    return new Vector2D(x0, x1);
  }
}
