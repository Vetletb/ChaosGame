package edu.ntnu.idatt2003.model.math.mathModel;

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

  /**
   * Scales the vector by the given scalar.
   *
   * @param scalar the scalar to scale the vector by.
   */
  public Vector2D scale(int scalar) {
    x0 = x0 * scalar;
    x1 = x1 * scalar;
    return new Vector2D(x0, x1);
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
    return this.x0 == ((Vector2D) obj).getX0() && this.x1 == ((Vector2D) obj).getX1();
  }
}
