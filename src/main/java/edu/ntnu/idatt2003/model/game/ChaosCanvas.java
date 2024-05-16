package edu.ntnu.idatt2003.model.game;

import edu.ntnu.idatt2003.exceptions.InvalidPositiveIntException;
import edu.ntnu.idatt2003.exceptions.InvalidVectorRangeException;
import edu.ntnu.idatt2003.exceptions.IsNullException;
import edu.ntnu.idatt2003.model.math.mathModel.Matrix2x2;
import edu.ntnu.idatt2003.model.math.mathModel.Vector2D;
import edu.ntnu.idatt2003.model.math.transformation.AffineTransform2D;
import edu.ntnu.idatt2003.util.InputValidation;
import java.util.Arrays;

/**
 * A class representing a canvas for drawing chaos games.
 */
public class ChaosCanvas {
  private int[][] canvas;
  private final int width;
  private final int height;
  private Vector2D maxCoords;
  private Vector2D minCoords;
  private AffineTransform2D transformCoordsToIndices;
  private int[] newPixel;

  /**
   * Constructor for the ChaosCanvas class.
   * Throws an IllegalArgumentException if width or height is negative.
   *
   * @param width the width of the canvas.
   * @param height the height of the canvas.
   * @param minCoords the minimum coordinates of the canvas.
   * @param maxCoords the maximum coordinates of the canvas.
   *
   * @throws InvalidPositiveIntException if width is not positive,
   *      if height is not positive,
   *      if minCoords is null,
   *      if maxCoords is null
   * @throws IsNullException if minCoords or maxCoords are null,
   * @throws InvalidVectorRangeException if the minimum coordinates are greater than or equal to the
   *     maximum coordinates.
   */
  public ChaosCanvas(int width, int height, Vector2D minCoords, Vector2D maxCoords)
      throws InvalidPositiveIntException, IsNullException, InvalidVectorRangeException {
    InputValidation.validatePositiveInt(width, "width");
    InputValidation.validatePositiveInt(height, "height");

    setMinMaxCoords(minCoords, maxCoords);
    this.width = width;
    this.height = height;
    this.canvas = new int[width][height];
    setTransformCoordsToIndices();
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
   * Sets the minimum coordinates of the canvas.
   *
   * @param minCoords the minimum coordinates of the canvas.
   * @throws IsNullException if minCoords is null
   */
  private void setMinCoords(Vector2D minCoords) throws IsNullException {
    InputValidation.validateNotNull(minCoords, "minCoords");
    this.minCoords = minCoords;
  }

  /**
   * Sets the maximum coordinates of the canvas.
   *
   * @param maxCoords the maximum coordinates of the canvas.
   * @throws IsNullException if maxCoords is null
   */
  private void setMaxCoords(Vector2D maxCoords) throws IsNullException {
    InputValidation.validateNotNull(maxCoords, "maxCoords");
    this.maxCoords = maxCoords;
  }

  /**
   * Sets the minimum and maximum coordinates of the canvas.
   *
   * @param minCoords the minimum coordinates of the canvas.
   * @param maxCoords the maximum coordinates of the canvas.
   *
   * @throws IsNullException if minCoords is null,
   *      if maxCoords is null
   */
  public void setMinMaxCoords(Vector2D minCoords, Vector2D maxCoords)
      throws IsNullException, InvalidVectorRangeException {
    setMinCoords(minCoords);
    setMaxCoords(maxCoords);

    InputValidation.validateVectorRange(minCoords, maxCoords);

    setTransformCoordsToIndices();
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
    canvas[x][y] += 1;
    int value = canvas[x][y];
    newPixel = new int[]{x, y, value};
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

  /**
   * Returns the new pixel.
   *
   * @return the new pixel.
   */
  public int[] getNewPixel() {
    return newPixel;
  }

  /**
   * Returns the width of the canvas.
   *
   * @return the width of the canvas.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Returns the height of the canvas.
   *
   * @return the height of the canvas.
   */
  public int getHeight() {
    return height;
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
    return Arrays.deepEquals(canvas, ((ChaosCanvas) obj).getCanvasArray())
        && width == ((ChaosCanvas) obj).getWidth()
        && height == ((ChaosCanvas) obj).getHeight()
        && maxCoords.equals(((ChaosCanvas) obj).maxCoords)
        && minCoords.equals(((ChaosCanvas) obj).minCoords)
        && transformCoordsToIndices.equals(((ChaosCanvas) obj).transformCoordsToIndices)
        && Arrays.equals(newPixel, ((ChaosCanvas) obj).newPixel);
  }
}

