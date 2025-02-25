package edu.ntnu.idatt2003.controller;

import edu.ntnu.idatt2003.exceptions.InvalidPositiveIntException;
import edu.ntnu.idatt2003.model.game.ChaosGame;
import edu.ntnu.idatt2003.util.ExceptionLogger;
import edu.ntnu.idatt2003.view.components.ViewCanvas;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * The controller for the canvas in the chaos game.
 */
public class CanvasController {

  //Constants for the animation
  public static final int RUN_SECONDS = 3;
  public static final int FPS = 60;
  public static final double K = 0.05;

  private ChaosGame chaosGame;
  private final ViewCanvas viewCanvas;
  private Timeline timeline;
  private ExceptionLogger exceptionLogger;
  private MessageController messageController;

  public CanvasController(ViewCanvas viewCanvas) {
    this.viewCanvas = viewCanvas;
  }

  /**
   * Sets the chaos game to a new chaos game.
   *
   * @param chaosGame the chaos game to be set
   */
  public void setChaosGame(ChaosGame chaosGame) {
    this.chaosGame = chaosGame;
  }

  /**
   * Sets the exception logger to a new exception logger.
   *
   * @param exceptionLogger the exception logger to be set
   */
  public void setLogger(ExceptionLogger exceptionLogger) {
    this.exceptionLogger = exceptionLogger;
  }

  /**
   * Sets the message controller to a new message controller.
   *
   * @param messageController the message controller to be set
   */
  public void setMessageController(MessageController messageController) {
    this.messageController = messageController;
  }

  /**
   * Resets the gui canvas.
   */
  public void resetViewCanvas() {
    stopTimeline();
    viewCanvas.reset();
  }

  /**
   * Draws a pixel on the viewCanvas. The color of the pixel is determined by how many times the
   * pixel has been visited.
   */
  private void drawPixel(int[] pixel) {
    int x = pixel[1];
    int y = pixel[0];
    int value = pixel[2];
    int blue = 100;
    int green = 0;
    int red = 0;
    for (int i = 0; i < value && i < 10; i++) {
      blue += 15;
      green += 20;
      red += 6;
    }
    int[] rgbColor = {red, green, blue};
    int[] scaledCoordinates = scaleCoordinates(x, y);
    viewCanvas.drawPoint(scaledCoordinates[0], scaledCoordinates[1], rgbColor);
  }

  /**
   * Draws the current pixel on the viewCanvas.
   */
  public void drawCurrentPixel() {
    drawPixel(chaosGame.getCanvas().getNewPixel());
  }

  /**
   * Scales the coordinates of the chaos game to the viewCanvas.
   *
   * @param x the x-coordinate to scale.
   * @param y the y-coordinate to scale.
   * @return the scaled coordinates.
   */
  private int[] scaleCoordinates(int x, int y) {
    int scaledX = (int) (
        x * viewCanvas.getCanvas().getWidth() / chaosGame.getCanvas().getWidth());
    int scaledY = (int) (
        y * viewCanvas.getCanvas().getHeight() / chaosGame.getCanvas().getHeight());
    return new int[]{scaledX, scaledY};
  }

  /**
   * Animates the chaos game by running a given number of iterations.
   *
   * @param iterations the number of iterations to animate
   */
  public void animateIterations(int iterations)  {
    if (iterations <= 0) {
      try {
        throw new InvalidPositiveIntException("Iterations must be a positive number");
      } catch (InvalidPositiveIntException e) {
        exceptionLogger.logWarning(e);
        messageController.showErrorPopup(e.getMessage());
      }
      return;
    }

    final int [] x = {0};

    final int [] totalSteps = {0};
    timeline = new Timeline();
    KeyFrame keyFrame = new KeyFrame(Duration.millis(1000.0 / FPS), e -> {
      int steps = (int)
          (iterations * K / (Math.exp(FPS * RUN_SECONDS * K) - 1) * Math.exp(K * x[0]));
      try {
        if (steps != 0) {
          long startTime = System.currentTimeMillis();
          chaosGame.runSteps(steps);
          long endTime = System.currentTimeMillis();
          if (endTime - startTime  > 1000.0 / FPS * 1.5) {
            messageController.showErrorPopup("The chaos game is taking too long to run, "
                + "try decreasing the number of iterations.");
            timeline.stop();
          }
        }
      } catch (InvalidPositiveIntException ex) {
        exceptionLogger.logSevere(ex);
        messageController.showErrorPopup(MainController.UNEXPECTED_EXCEPTION);
        timeline.stop();
      }
      x[0]++;
      totalSteps[0] += steps;
    });
    timeline.getKeyFrames().add(keyFrame);
    timeline.setCycleCount(FPS * RUN_SECONDS);
    timeline.setOnFinished(e -> {
      try {
        if (iterations - totalSteps[0] > 0) {
          chaosGame.runSteps(iterations - totalSteps[0]);
        }
      } catch (InvalidPositiveIntException ex) {
        exceptionLogger.logSevere(ex);
        messageController.showErrorPopup(MainController.UNEXPECTED_EXCEPTION);
      }
    });
    timeline.play();
  }

  /**
   * Stops the animation of the chaos game if it's currently running.
   */
  private void stopTimeline() {
    if (timeline != null && timeline.getStatus() == Timeline.Status.RUNNING) {
      timeline.stop();
    }
  }

  /**
   * Rescales the canvas.
   */
  public void rescaleCanvas() {
    int[][] array = chaosGame.getCanvas().getCanvasArray();
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        int[] pixel = {i, j, array[i][j]};
        if (pixel[2] != 0) {
          drawPixel(pixel);
        }
      }
    }
  }
}
