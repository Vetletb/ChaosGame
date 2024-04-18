package edu.ntnu.idatt2003.view.components;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A class for the canvas in the view.
 */
public class ViewCanvas {
  Canvas canvas;
  GraphicsContext gc;

  /**
   * Constructor for the ViewCanvas class.
   *
   * @param width the width of the canvas.
   * @param height the height of the canvas.
   */
  public ViewCanvas(int width, int height) {
    this.canvas = new Canvas(width, height);
    gc = canvas.getGraphicsContext2D();
    gc.setFill(Color.BLACK);
    gc.fillRect(0, 0, width, height);
  }

  /**
   * Draws a point on the canvas.
   *
   * @param x the x-coordinate of the point.
   * @param y the y-coordinate of the point.
   * @param rgbColor the color of the point.
   */
  public void drawPoint(int x, int y, int[] rgbColor) {
    int red = rgbColor[0];
    int green = rgbColor[1];
    int blue = rgbColor[2];
    Color color = Color.rgb(red, green, blue);
    gc.setFill(color);
    gc.fillOval(x, y, 2, 2);
  }

  public Canvas getCanvas() {
    return canvas;
  }
}
