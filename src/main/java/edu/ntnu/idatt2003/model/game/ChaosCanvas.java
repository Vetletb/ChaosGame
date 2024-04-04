package edu.ntnu.idatt2003.model.game;

import edu.ntnu.idatt2003.model.math.mathModel.Matrix2x2;
import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;
import edu.ntnu.idatt2003.model.math.transformation.AffineTransform2D;

/**
 * A class representing a canvas for drawing chaos games.
 */
public class ChaosCanvas {

  private int[][] canvas;
  private final int width;
  private final int height;
  private final Vector2D maxCoords;
  private final Vector2D minCoords;
  private AffineTransform2D transformCoordsToIndices;

  /**
   * Constructor for the ChaosCanvas class.
   * Throws an IllegalArgumentException if width or height is negative.
   *
   * @param width the width of the canvas.
   * @param height the height of the canvas.
   * @param minCoords the minimum coordinates of the canvas.
   * @param maxCoords the maximum coordinates of the canvas.
   *
   * @throws IllegalArgumentException if width or height is not positive.
   */
  public ChaosCanvas(int width, int height, Vector2D minCoords, Vector2D maxCoords) {
    validatePositive(width, "width");
    validatePositive(height, "height");
    this.width = width;
    this.height = height;
    this.minCoords = minCoords;
    this.maxCoords = maxCoords;
    this.canvas = new int[width][height];
    setTransformCoordsToIndices();
  }

  /**
   * Validates that the given value is positive.
   *
   * @param value the value to validate.
   * @param name the name of the value.
   */
  private void validatePositive(int value, String name) {
    if (value <= 0) {
      throw new IllegalArgumentException(name + " must be positive");
    }
  }

  /**
   * Sets the transformation from coordinates to indices.
   */
  private void setTransformCoordsToIndices() {
    Matrix2x2 matrix = new Matrix2x2(
        0,
        (height - 1) / (minCoords.getX1() - maxCoords.getX1()),
        (width - 1) / (maxCoords.getX0() - minCoords.getX0()),
        0);

    Vector2D vector = new Vector2D(
        (height - 1) * maxCoords.getX1() / (maxCoords.getX1() - minCoords.getX1()),
        (width - 1) * minCoords.getX0() / (minCoords.getX0() - maxCoords.getX0()));

    transformCoordsToIndices = new AffineTransform2D(matrix, vector);
  }

  /**
   * Returns the pixel value at the given point.
   *
   * @param point the point to get the pixel value from.
   *
   * @return the pixel value at the given point.
   */
  public int getPixel(Vector2D point) {
    Vector2D pixel = transformCoordsToIndices.transform(point);
    int x = (int) pixel.getX0();
    int y = (int) pixel.getX1();
    return canvas[x][y];
  }

  /**
   * Puts a pixel at the given point.
   *
   * @param point the point to put the pixel at.
   */
  public void putPixel(Vector2D point) {
    Vector2D pixel = transformCoordsToIndices.transform(point);
    int x = (int) pixel.getX0();
    int y = (int) pixel.getX1();
    canvas[x][y] = 1;
  }

  /**
   * Returns the canvas array.
   *
   * @return the canvas array.
   */
  public int[][] getCanvasArray() {
    return canvas;
  }

  /**
   * Clears the canvas.
   */
  public void clear() {
    canvas = new int[width][height];
  }
}