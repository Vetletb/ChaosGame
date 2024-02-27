package edu.ntnu.idatt2003;

/**
 * A class representing a complex number.
 */
public class Complex extends Vector2D {

  /**
   * Constructor for the Complex class.
   */
  public Complex(double realPart, double imaginaryPart) {
    super(realPart, imaginaryPart);
  }

  /**
   * Method for computing the square of a complex number.
   *
   * @return the square of the complex number as a new complex number.
   */
  public Complex sqrt() {
    double realPart =
        Math.sqrt((Math.sqrt(Math.pow(getX0(), 2) + Math.pow(getX1(), 2)) + getX0()) / 2);
    double imaginaryPart =
        Math.signum(getX1()) * Math.sqrt((Math.sqrt(Math.pow(getX0(), 2)
            + Math.pow(getX1(), 2)) - getX0()) / 2);
    return new Complex(realPart, imaginaryPart);
  }
}
