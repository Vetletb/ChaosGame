package edu.ntnu.idatt2003.model.math.mathModel;

/**
 * A class representing a 2x2 matrix.
 */
public class Matrix2x2 {
  private final double a00;
  private final double a01;
  private final double a10;
  private final double a11;

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

  /**
   * gets the a00 value of the matrix.
   *
   * @return the a00 value of the matrix.
   */
  public double getA00() {
    return a00;
  }

  /**
   * gets the a01 value of the matrix.
   *
   * @return the a01 value of the matrix.
   */
  public double getA01() {
    return a01;
  }

  /**
   * gets the a10 value of the matrix.
   *
   * @return the a10 value of the matrix.
   */
  public double getA10() {
    return a10;
  }

  /**
   * gets the a11 value of the matrix.
   *
   * @return the a11 value of the matrix.
   */
  public double getA11() {
    return a11;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Matrix2x2 matrix = (Matrix2x2) obj;
    return a00 == matrix.getA00() && a01 == matrix.getA01()
        && a10 == matrix.getA10() && a11 == matrix.getA11();

  }
}
