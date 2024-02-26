package edu.ntnu.idatt2003;

/**
 * A class representing a 2D vector.
 */
public class Vector2D {
  private double x0;
  private double x1;

  /**
   * Constructor for the Vector2D class.
   */
  public Vector2D(double x0, double x1) {
    this.x0 = x0;
    this.x1 = x1;
  }

  /**
   * Returns the x0 value of the vector.
   *
   * @return the x0 value of the vector.
   */
  public double getX0() {
    return x0;
  }

  /**
   * Returns the x1 value of the vector.
   *
   * @return the x1 value of the vector.
   */
  public double getX1() {
    return x1;
  }

  /**
   * Adds the given vector to this vector.
   *
   * @param vector2 the vector to add to this vector.
   */
  public Vector2D add(Vector2D vector2) {
    x0 += vector2.getX0();
    x1 += vector2.getX1();
    return new Vector2D(x0, x1);
  }

  /**
   * Subtracts the given vector from this vector.
   *
   * @param vector2 the vector to subtract from this vector.
   */
  public Vector2D subtract(Vector2D vector2) {
    x0 -= vector2.getX0();
    x1 -= vector2.getX1();
    return new Vector2D(x0, x1);
  }
}
